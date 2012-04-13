/*
 * Created on 14/dic/2011
 * Copyright 2010 by Andrea Vacondio (andrea.vacondio@gmail.com).
 *
 * This program is free software; you can redistribute it and/or modify it under the terms of the 
 * GNU General Public License as published by the Free Software Foundation; 
 * either version 2 of the License.
 * This program is distributed in the hope that it will be useful, but WITHOUT ANY WARRANTY; 
 * without even the implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. 
 * See the GNU General Public License for more details.
 * You should have received a copy of the GNU General Public License along with this program; 
 * if not, write to the Free Software Foundation, Inc., 
 *  59 Temple Place, Suite 330, Boston, MA 02111-1307 USA
 */
package org.pdfsam;

import org.apache.commons.lang3.time.DurationFormatUtils;
import org.apache.commons.lang3.time.StopWatch;
import org.noos.xing.mydoggy.plaf.ui.util.SwingUtil;
import org.pdfsam.context.DefaultI18nContext;
import org.pdfsam.gui.AboutPanel;
import org.pdfsam.gui.MainFrame;
import org.pdfsam.gui.WelcomePanel;
import org.pdfsam.gui.menu.MenuType;
import org.pdfsam.gui.preference.PreferencesPanel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Entry point to start pdfsam
 * 
 * @author Andrea Vacondio
 * 
 */
public final class App {
    private static final Logger LOG = LoggerFactory.getLogger(App.class);
    private static MainFrame mainFrame = null;

    private App() {
        // hide
    }

    public static void main(String[] args) {
        LOG.info(DefaultI18nContext.getInstance().getI18n().tr("Starting pdfsam"));
        StopWatch stopWatch = new StopWatch();
        stopWatch.start();

        try {
            mainFrame = new MainFrame();
            // TODO add plugins to Modules menu
            mainFrame.addSystemContentAction(MenuType.EDIT, new PreferencesPanel());
            mainFrame.addSystemContentAction(MenuType.HELP, new WelcomePanel());
            mainFrame.addSystemContentAction(MenuType.HELP, new AboutPanel());

            SwingUtil.centrePositionOnScreen(mainFrame);
            mainFrame.setVisible(true);
        } catch (RuntimeException e) {
            LOG.error(DefaultI18nContext.getInstance().getI18n().tr("Error starting pdfsam."), e);
            throw e;
        }
        stopWatch.stop();
        LOG.info(DefaultI18nContext.getInstance().getI18n()
                .tr("started in {0}", DurationFormatUtils.formatDurationWords(stopWatch.getTime(), true, true)));
    }

    /**
     * @return the mainFrame
     */
    public static MainFrame getMainFrame() {
        return mainFrame;
    }

}
