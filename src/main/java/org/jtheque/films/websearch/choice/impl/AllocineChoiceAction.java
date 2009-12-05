package org.jtheque.films.websearch.choice.impl;

/*
 * This file is part of JTheque.
 *
 * JTheque is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License.
 *
 * JTheque is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with JTheque.  If not, see <http://www.gnu.org/licenses/>.
 */

import org.jtheque.core.managers.Managers;
import org.jtheque.core.managers.log.ILoggingManager;
import org.jtheque.core.managers.persistence.able.Entity;
import org.jtheque.primary.view.impl.choice.AbstractChoiceAction;

import java.awt.Desktop;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

/**
 * An action to go to the search page on allocine with the selected item.
 *
 * @author Baptiste Wicht
 */
public final class AllocineChoiceAction extends AbstractChoiceAction {
    @Override
    public boolean canDoAction(String action) {
        return "allocine".equals(action);
    }

    @Override
    public void execute() {
        String searchAllocine = ((Entity) getSelectedItem()).getDisplayableText().replace(" ", "+");

        if (Desktop.isDesktopSupported() && Desktop.getDesktop().isSupported(Desktop.Action.BROWSE)) {
            try {
                Desktop.getDesktop().browse(new URI("http://www.allocine.fr/recherche/?motcle=" +
                        searchAllocine + "&f=3&rub=0"));
            } catch (IOException e) {
                Managers.getManager(ILoggingManager.class).getLogger(getClass()).error(e);
            } catch (URISyntaxException e) {
                Managers.getManager(ILoggingManager.class).getLogger(getClass()).error(e);
            }
        }
    }
}