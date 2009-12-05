package org.jtheque.films.websearch.view.actions;

import org.jtheque.core.managers.Managers;
import org.jtheque.core.managers.resource.IResourceManager;
import org.jtheque.core.managers.resource.ImageType;
import org.jtheque.core.managers.view.impl.actions.JThequeAction;
import org.jtheque.films.websearch.WebSearchModule;
import org.jtheque.primary.controller.able.IChoiceController;

import javax.annotation.Resource;
import java.awt.event.ActionEvent;

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

/**
 * An action to search an object on a specific site.
 *
 * @author Baptiste Wicht
 */
public final class AcSearchOnWeb extends JThequeAction {
    private static final long serialVersionUID = 8223496137147203300L;
    private final String site;
    private final String data;

    @Resource
    private IChoiceController choiceController;

    /**
     * Construct a new AcSearchOnWeb.
     *
     * @param key  The internationalization key of the action.
     * @param site The site of the action.
     * @param data The data type.
     */
    public AcSearchOnWeb(String key, String site, String data) {
        super(key);

        this.site = site;
        this.data = data;

        setIcon(Managers.getManager(IResourceManager.class).getIcon(WebSearchModule.IMAGE_BASE_NAME, "search", ImageType.PNG));
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        choiceController.setAction(site);
        choiceController.setContent(data);
        choiceController.displayView();
    }
}
