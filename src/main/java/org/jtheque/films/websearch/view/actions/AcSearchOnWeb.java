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
