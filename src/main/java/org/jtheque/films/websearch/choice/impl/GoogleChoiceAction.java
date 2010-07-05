package org.jtheque.films.websearch.choice.impl;

/*
 * Copyright JTheque (Baptiste Wicht)
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
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
 * An action to go to the search page on Google with the selected item.
 *
 * @author Baptiste Wicht
 */
public final class GoogleChoiceAction extends AbstractChoiceAction {
    @Override
    public boolean canDoAction(String action) {
        return "google".equals(action);
    }

    @Override
    public void execute() {
        String searchGoogle = ((Entity) getSelectedItem()).getDisplayableText().replace(" ", "+");

        if (Desktop.isDesktopSupported() && Desktop.getDesktop().isSupported(Desktop.Action.BROWSE)) {
            try {
                Desktop.getDesktop().browse(new URI("http://www.google.ch/search?q=" + searchGoogle));
            } catch (IOException e) {
                Managers.getManager(ILoggingManager.class).getLogger(getClass()).error(e);
            } catch (URISyntaxException e) {
                Managers.getManager(ILoggingManager.class).getLogger(getClass()).error(e);
            }
        }
    }
}
