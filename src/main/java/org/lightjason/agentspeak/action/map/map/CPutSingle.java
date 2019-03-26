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

package org.lightjason.agentspeak.action.map.map;

import org.lightjason.agentspeak.action.map.IMapApplySingle;
import org.lightjason.agentspeak.common.IPath;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.Map;


/**
 * adds an single element to multiple map arguments.
 * First argument is a key, second the value, all
 * other values are map references, the key-value pair
 * is added to all maps
 *
 * {@code .collection/map/putsingle( "key", "value", Map1, Map2 );}
 */
public final class CPutSingle extends IMapApplySingle<Map<Object, Object>>
{
    /**
     * serial id
     */
    private static final long serialVersionUID = 2571810828014437518L;
    /**
     * action name
     */
    private static final IPath NAME = namebyclass( CPutSingle.class, "collection", "map" );

    @Nonnull
    @Override
    public IPath name()
    {
        return NAME;
    }

    @Override
    protected void apply( @Nonnull final Map<Object, Object> p_instance, @Nonnull final Object p_key, @Nullable final Object p_value )
    {
        p_instance.put( p_key, p_value );
    }

}
