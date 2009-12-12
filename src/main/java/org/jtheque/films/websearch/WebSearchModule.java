package org.jtheque.films.websearch;

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
import org.jtheque.core.managers.feature.Feature;
import org.jtheque.core.managers.feature.Feature.FeatureType;
import org.jtheque.core.managers.feature.IFeatureManager;
import org.jtheque.core.managers.module.annotations.Module;
import org.jtheque.core.managers.module.annotations.Plug;
import org.jtheque.core.managers.module.annotations.UnPlug;
import org.jtheque.films.IFilmsModule;
import org.jtheque.films.utils.Constants;
import org.jtheque.primary.view.impl.choice.ChoiceAction;
import org.jtheque.primary.view.impl.choice.ChoiceActionFactory;
import org.jtheque.utils.collections.ArrayUtils;

import javax.annotation.Resource;

/**
 * A module to search films, actors and realizers on the web.
 *
 * @author Baptiste Wicht
 */
@Module(id = "jtheque-websearch-module", i18n = "classpath:org/jtheque/films/websearch/i18n/web",
        version = "1.4.1", core = "2.0.2", jarFile = "jtheque-websearch-module-1.4.1.jar",
        dependencies = {"jtheque-films-module"}, updateURL = "http://jtheque.developpez.com/public/versions/WebSearchModule.versions")
public final class WebSearchModule {
    private Feature webFilmFeature;
    private Feature webActorsFeature;
    private Feature webRealizersFeature;

    private ChoiceAction[] choiceActions;

    @Resource
    private IFilmsModule filmsModule;

    public static final String IMAGE_BASE_NAME = "org/jtheque/films/websearch/images";

    /**
     * Plug the module.
     */
    @Plug
    public void plug() {
        IFeatureManager manager = Managers.getManager(IFeatureManager.class);

        addFilmFeatures(manager);
        addActorsFeature(manager);
        addRealizersFeature(manager);

        for (ChoiceAction action : choiceActions) {
            ChoiceActionFactory.addChoiceAction(action);
        }
    }

    /**
     * Add film features.
     *
     * @param manager The feature manager.
     */
    private void addFilmFeatures(IFeatureManager manager) {
        webFilmFeature = manager.createFeature(3, FeatureType.ACTIONS, "menu.jtheque.films.web");
        webFilmFeature.setBaseName(Constants.IMAGE_BASE_NAME);
        webFilmFeature.setIcon("web");

        manager.addSubFeature(webFilmFeature, "searchFilmOnGoogleAction", FeatureType.ACTION, 1);
        manager.addSubFeature(webFilmFeature, "searchFilmOnAllocineAction", FeatureType.ACTION, 2);

        filmsModule.getFilmsFeature().addSubFeature(webFilmFeature);
    }

    /**
     * Add actor features.
     *
     * @param manager The feature manager.
     */
    private void addActorsFeature(IFeatureManager manager) {
        webActorsFeature = manager.createFeature(3, FeatureType.ACTIONS, "menu.jtheque.actors.web");
        webActorsFeature.setBaseName(Constants.IMAGE_BASE_NAME);
        webActorsFeature.setIcon("web");

        manager.addSubFeature(webActorsFeature, "searchActorOnGoogleAction", FeatureType.ACTION, 1);
        manager.addSubFeature(webActorsFeature, "searchActorOnAllocineAction", FeatureType.ACTION, 2);

        filmsModule.getActorsFeature().addSubFeature(webActorsFeature);
    }

    /**
     * Add realizer features.
     *
     * @param manager The feature manager.
     */
    private void addRealizersFeature(IFeatureManager manager) {
        webRealizersFeature = manager.createFeature(3, FeatureType.ACTIONS, "menu.jtheque.realizers.web");
        webRealizersFeature.setBaseName(Constants.IMAGE_BASE_NAME);
        webRealizersFeature.setIcon("web");

        manager.addSubFeature(webRealizersFeature, "searchRealizerOnGoogleAction", FeatureType.ACTION, 1);
        manager.addSubFeature(webRealizersFeature, "searchRealizerOnAllocineAction", FeatureType.ACTION, 2);

        filmsModule.getRealizersFeature().addSubFeature(webRealizersFeature);
    }

    /**
     * Unplug the module.
     */
    @UnPlug
    public void unplug() {
        for (ChoiceAction action : choiceActions) {
            ChoiceActionFactory.removeChoiceAction(action);
        }

        filmsModule.getRealizersFeature().removeSubFeature(webRealizersFeature);
        filmsModule.getFilmsFeature().removeSubFeature(webFilmFeature);
        filmsModule.getActorsFeature().removeSubFeature(webActorsFeature);
    }

    /**
     * Set the choice actions.
     *
     * @param choiceActions The choice actions.
     */
    public void setChoiceActions(ChoiceAction[] choiceActions) {
        this.choiceActions = ArrayUtils.copyOf(choiceActions);
    }
}