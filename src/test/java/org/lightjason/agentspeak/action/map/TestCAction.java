/*
 * @cond LICENSE
 * ######################################################################################
 * # LGPL License                                                                       #
 * #                                                                                    #
 * # This file is part of the LightJason AgentSpeak(L++)                                #
 * # Copyright (c) 2015-19, LightJason (info@lightjason.org)                            #
 * # This program is free software: you can redistribute it and/or modify               #
 * # it under the terms of the GNU Lesser General Public License as                     #
 * # published by the Free Software Foundation, either version 3 of the                 #
 * # License, or (at your option) any later version.                                    #
 * #                                                                                    #
 * # This program is distributed in the hope that it will be useful,                    #
 * # but WITHOUT ANY WARRANTY; without even the implied warranty of                     #
 * # MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the                      #
 * # GNU Lesser General Public License for more details.                                #
 * #                                                                                    #
 * # You should have received a copy of the GNU Lesser General Public License           #
 * # along with this program. If not, see http://www.gnu.org/licenses/                  #
 * ######################################################################################
 * @endcond
 */

package org.lightjason.agentspeak.action.map;

import com.codepoetics.protonpack.StreamUtils;
import com.google.common.collect.HashMultimap;
import com.google.common.collect.Multimap;
import org.junit.Assert;
import org.junit.Test;
import org.lightjason.agentspeak.language.CRawTerm;
import org.lightjason.agentspeak.language.ITerm;
import org.lightjason.agentspeak.language.execution.IContext;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;


/**
 * generic map actions
 */
public final class TestCAction
{
    /**
     * test clear map
     */
    @Test
    public void clearmap()
    {
        final Map<Integer, Integer> l_map = StreamUtils.windowed( IntStream.range( 100, 120 ).boxed(), 2 )
                                                       .collect( Collectors.toMap( i -> i.get( 0 ), i -> i.get( 1 ) ) );
        final Multimap<Integer, Integer> l_multimap = HashMultimap.create();
        IntStream.range( 0, 5 ).forEach( i -> IntStream.range( i, i + 5 ).forEach( j -> l_map.put( i, j ) ) );

        new CMapClear().execute(
            false, IContext.EMPTYPLAN,
            Stream.of( l_map, l_multimap ).map( CRawTerm::of ).collect( Collectors.toList() ),
            Collections.emptyList()
        );

        Assert.assertTrue( l_map.isEmpty() );
        Assert.assertTrue( l_multimap.isEmpty() );

    }

    /**
     * test empty map
     */
    @Test
    public void emptymap()
    {
        final List<ITerm> l_return = new ArrayList<>();

        new CMapIsEmpty().execute(
            false, IContext.EMPTYPLAN,
            Stream.of( new ArrayList<>(), new HashSet<>(), HashMultimap.create(), new HashMap<>(), Stream.of( "1", 2 ).collect( Collectors.toList() ), new Object() )
                  .map( CRawTerm::of )
                  .collect( Collectors.toList() ),
            l_return
        );

        Assert.assertEquals( 6, l_return.size() );
        Assert.assertArrayEquals( Stream.of( false, false, true, true, false, false ).toArray(), l_return.stream().map( ITerm::<Boolean>raw ).toArray() );
    }

    /**
     * test map size
     */
    @Test
    public void mapsize()
    {
        final Map<String, String> l_map = new HashMap<>();
        l_map.put( "a", "b" );
        l_map.put( "c", "d" );

        final HashMultimap<String, String> l_multimap = HashMultimap.create();
        l_multimap.put( "a", "b" );
        l_multimap.put( "a", "x" );
        l_multimap.put( "c", "d" );

        final List<ITerm> l_return = new ArrayList<>();


        new CMapSize().execute(
            false, IContext.EMPTYPLAN,
            Stream.of( new ArrayList<>(), new HashSet<>(), l_multimap, l_map, Stream.of( "1", 2 ).collect( Collectors.toList() ), new Object() )
                  .map( CRawTerm::of )
                  .collect( Collectors.toList() ),
            l_return
        );

        Assert.assertArrayEquals( Stream.of( 0L, 0L, 3L, 2L, 0L, 0L ).toArray(), l_return.stream().map( ITerm::raw ).toArray() );
    }
}
