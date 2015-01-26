package hex.rpg.app.campaign.gui;

import hex.rpg.app.campaign.action.GetCampaignAction;
import hex.rpg.app.campaign.node.CampaignChildFactory;
import hex.rpg.app.campaign.node.RpgRootNode;
import hex.rpg.core.domain.campaign.Campaign;
import java.awt.BorderLayout;
import java.util.ArrayList;
import java.util.List;
import org.netbeans.api.settings.ConvertAsProperties;
import org.openide.awt.ActionID;
import org.openide.awt.ActionReference;
import org.openide.explorer.ExplorerManager;
import org.openide.explorer.view.BeanTreeView;
import org.openide.explorer.view.TreeView;
import org.openide.windows.TopComponent;
import org.openide.util.NbBundle.Messages;

@ConvertAsProperties(dtd = "-//hex.rpg.app.campaign.gui//CampaignOverview//EN",
        autostore = false)
@TopComponent.Description(preferredID = CampaignOverviewTopComponent.PREFERRED_ID,
        iconBase = "hex/rpg/app/campaign/gui/Eye.png",
        persistenceType = TopComponent.PERSISTENCE_ALWAYS
)
@TopComponent.Registration(mode = "explorer", openAtStartup = true)
@ActionID(category = "Window", id = "hex.rpg.app.campaign.gui.CampaignOverviewTopComponent")
@ActionReference(path = "Menu/Window" /*, position = 333 */)
@TopComponent.OpenActionRegistration(displayName = "#CTL_CampaignOverviewAction",
        preferredID = CampaignOverviewTopComponent.PREFERRED_ID
)
@Messages({
    "CTL_CampaignOverviewAction=CampaignOverview",
    "CTL_CampaignOverviewTopComponent=CampaignOverview Window",
    "HINT_CampaignOverviewTopComponent=This is a CampaignOverview window"
})
public final class CampaignOverviewTopComponent extends TopComponent implements ExplorerManager.Provider {

    public static final String PREFERRED_ID = "CampaignOverviewTopComponent";
    private final ExplorerManager explorerManager = new ExplorerManager();
    private final TreeView treeView;
    private final List<Campaign> campaignList = new ArrayList<>();
    private final CampaignChildFactory campaignChildeFactory = new CampaignChildFactory();

    public CampaignOverviewTopComponent() {
        initComponents();

        setName(Bundle.CTL_CampaignOverviewTopComponent());
        setToolTipText(Bundle.HINT_CampaignOverviewTopComponent());
        treeView = new BeanTreeView();
        setLayout(new BorderLayout());
        add(treeView, BorderLayout.CENTER);
        add(campaignUpdatePanel, BorderLayout.SOUTH);
        treeView.setRootVisible(true);
        explorerManager.setRootContext(new RpgRootNode(campaignChildeFactory));
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        campaignUpdatePanel = new javax.swing.JPanel();
        refreshViewButton = new javax.swing.JButton();
        viewerScrollPane = new javax.swing.JScrollPane();

        org.openide.awt.Mnemonics.setLocalizedText(refreshViewButton, org.openide.util.NbBundle.getMessage(CampaignOverviewTopComponent.class, "CampaignOverviewTopComponent.refreshViewButton.text")); // NOI18N
        refreshViewButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                refreshViewButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout campaignUpdatePanelLayout = new javax.swing.GroupLayout(campaignUpdatePanel);
        campaignUpdatePanel.setLayout(campaignUpdatePanelLayout);
        campaignUpdatePanelLayout.setHorizontalGroup(
            campaignUpdatePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(campaignUpdatePanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(refreshViewButton, javax.swing.GroupLayout.PREFERRED_SIZE, 115, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(273, Short.MAX_VALUE))
        );
        campaignUpdatePanelLayout.setVerticalGroup(
            campaignUpdatePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(campaignUpdatePanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(refreshViewButton)
                .addContainerGap(62, Short.MAX_VALUE))
        );

        setLayout(new java.awt.BorderLayout());
        add(viewerScrollPane, java.awt.BorderLayout.CENTER);
    }// </editor-fold>//GEN-END:initComponents

    private void refreshViewButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_refreshViewButtonActionPerformed
        GetCampaignAction getCampaignAction = new GetCampaignAction();
        getCampaignAction.performAction();
        Campaign campaign = getCampaignAction.getCampaign();
        if (!campaignList.contains(campaign)) {
            campaignList.add(campaign);
        }
        campaignChildeFactory.refreshList(campaignList);
    }//GEN-LAST:event_refreshViewButtonActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel campaignUpdatePanel;
    private javax.swing.JButton refreshViewButton;
    private javax.swing.JScrollPane viewerScrollPane;
    // End of variables declaration//GEN-END:variables
    @Override
    public void componentOpened() {
    }

    @Override
    public void componentClosed() {
    }

    void writeProperties(java.util.Properties p) {
        p.setProperty("version", "1.0");
    }

    void readProperties(java.util.Properties p) {
        String version = p.getProperty("version");
    }

    @Override
    public ExplorerManager getExplorerManager() {
        return explorerManager;
    }
}
