import org.jlab.io.hipo.*;
import org.jlab.clas.physics.Particle;
import org.jlab.clas.physics.PhysicsEvent;
import org.jlab.io.base.DataEvent;
import org.jlab.io.base.DataBank;
import org.jlab.groot.data.H1F;
import org.jlab.groot.data.H2F;
import org.jlab.groot.math.F1D;
import org.jlab.groot.group.DataGroup;
import org.jlab.groot.graphics.EmbeddedCanvasTabbed;
import javax.swing.JFrame;
import org.jlab.groot.fitter.DataFitter;

import org.clas.viewer.AnalysisMonitor;


public void fitMC(H1F himc, F1D f1mc) {
        double mean  = himc.getDataX(himc.getMaximumBin());
        double amp   = himc.getBinContent(himc.getMaximumBin());
        double sigma = himc.getRMS();
        f1mc.setParameter(0, amp);
        f1mc.setParameter(1, mean);
        f1mc.setParameter(2, sigma);
        f1mc.setRange(mean-2.0 * sigma,mean+2.0 * sigma);
        DataFitter.fit(f1mc, himc, "Q"); //No options uses error for sigma
        sigma = Math.abs(f1mc.getParameter(2));  
        f1mc.setRange(mean-2.0 * sigma,mean+2.0 * sigma);
        DataFitter.fit(f1mc, himc, "Q"); //No options uses error for sigma 
    }



// create histograms
DataGroup dgMC = new DataGroup(4,3);

for(int ifile=0; ifile<2; ifile++) {
    H1F hi_gen_p = new H1F("hi_gen_p_"+(ifile+1), "p gen (GeV)", "Counts", 100, 0, 10);
    hi_gen_p.setLineColor(ifile+1);
    dgMC.addDataSet(hi_gen_p, 0);
    H1F hi_gen_theta = new H1F("hi_gen_theta_"+(ifile+1), "#theta gen (deg)", "Counts", 100, 0, 40);
    hi_gen_theta.setLineColor(ifile+1);
    dgMC.addDataSet(hi_gen_theta, 1);
    H1F hi_gen_phi = new H1F("hi_gen_phi_"+(ifile+1), "#phi gen (deg)", "Counts", 100, -180, 180);
    hi_gen_phi.setLineColor(ifile+1);
    dgMC.addDataSet(hi_gen_phi, 2);
    H1F hi_zvertex_gen = new H1F("hi_zvertex_gen_"+(ifile+1), "Zvertex gen (cm)", "Counts", 180, -20.0, 20.0);
    hi_zvertex_gen.setLineColor(ifile+1);
    dgMC.addDataSet(hi_zvertex_gen, 3);



    H1F hi_rec_p = new H1F("hi_rec_p_"+(ifile+1), "p rec (GeV)", "Counts", 100, 0, 10);
    hi_rec_p.setLineColor(ifile+1);
    dgMC.addDataSet(hi_rec_p, 4);
    H1F hi_rec_theta = new H1F("hi_rec_theta_"+(ifile+1), "#theta rec (deg)", "Counts", 100, 0, 40);
    hi_rec_theta.setLineColor(ifile+1);
    dgMC.addDataSet(hi_rec_theta, 5);
    H1F hi_rec_phi = new H1F("hi_rec_phi_"+(ifile+1), "#phi rec (deg)", "Counts", 100, -180, 180);
    hi_rec_phi.setLineColor(ifile+1);
    dgMC.addDataSet(hi_rec_phi, 6);
    H1F hi_zvertex_neg = new H1F("hi_zvertex_neg_"+(ifile+1), "Zvertex rec (cm)", "Counts", 180, -20.0, 20.0);
    hi_zvertex_neg.setLineColor(ifile+1);
    dgMC.addDataSet(hi_zvertex_neg, 7);

    

    H1F hi_delta_p = new H1F("hi_delta_p_"+(ifile+1), "#Delta P / p(GeV)", "Counts", 100, -0.1, 0.1);
    hi_delta_p.setLineColor(ifile+1);
    dgMC.addDataSet(hi_delta_p, 8);

    H1F hi_delta_theta = new H1F("hi_delta_theta_"+(ifile+1), "#Delta #theta (deg)", "Counts", 100, -0.5, 0.5);
    hi_delta_theta.setLineColor(ifile+1);
    dgMC.addDataSet(hi_delta_theta, 9);

    H1F hi_delta_phi = new H1F("hi_delta_phi_"+(ifile+1), "#Delta #phi (deg)", "Counts", 100, -1.5, 1.5);
    hi_delta_phi.setLineColor(ifile+1);
    dgMC.addDataSet(hi_delta_phi, 10);

    H1F hi_delta_zvertex = new H1F("hi_delta_zvertex_"+(ifile+1), "#Delta Zvertex (cm)", "Counts", 100, -2.0, 2.0);
    hi_delta_zvertex.setLineColor(ifile+1);
    dgMC.addDataSet(hi_delta_zvertex, 11);
}						

DataGroup dgMC_ZTheta_grid = new DataGroup(4,2);
for(int ifile=0; ifile<2; ifile++) {
    H1F hi_delta_p_Zminus_Thetalow = new H1F("hi_delta_p_Zminus_Thetalow_"+(ifile+1), "#Delta P / p(GeV) (Z-, #theta low) cut", "Counts", 100, -0.1, 0.1);
    hi_delta_p_Zminus_Thetalow.setLineColor(ifile+1);
    dgMC_ZTheta_grid.addDataSet(hi_delta_p_Zminus_Thetalow, 0);

    H1F hi_delta_p_Zminus_Thetahigh = new H1F("hi_delta_p_Zminus_Thetahigh_"+(ifile+1), "#Delta P / p(GeV) (Z-, #theta high) cut", "Counts", 100, -0.1, 0.1);
    hi_delta_p_Zminus_Thetahigh.setLineColor(ifile+1);
    dgMC_ZTheta_grid.addDataSet(hi_delta_p_Zminus_Thetahigh, 1);
 
    H1F hi_delta_p_Zplus_Thetalow = new H1F("hi_delta_p_Zplus_Thetalow_"+(ifile+1), "#Delta P / p(GeV) (Z+, #theta low) cut", "Counts", 100, -0.1, 0.1);
    hi_delta_p_Zplus_Thetalow.setLineColor(ifile+1);
    dgMC_ZTheta_grid.addDataSet(hi_delta_p_Zplus_Thetalow, 2);
    
    H1F hi_delta_p_Zplus_Thetahigh = new H1F("hi_delta_p_Zplus_Thetahigh_"+(ifile+1), "#Delta P / p(GeV) (Z+, #theta high) cut", "Counts", 100, -0.1, 0.1);
    hi_delta_p_Zplus_Thetahigh.setLineColor(ifile+1);
    dgMC_ZTheta_grid.addDataSet(hi_delta_p_Zplus_Thetahigh, 3);
    


    H1F hi_delta_theta_Zminus_Thetalow = new H1F("hi_delta_theta_Zminus_Thetalow_"+(ifile+1), "#Delta #theta (deg) (Z-, #theta low) cut", "Counts", 100, -0.5, 0.5);
    hi_delta_theta_Zminus_Thetalow.setLineColor(ifile+1);
    dgMC_ZTheta_grid.addDataSet(hi_delta_theta_Zminus_Thetalow, 4);

    H1F hi_delta_theta_Zminus_Thetahigh = new H1F("hi_delta_theta_Zminus_Thetahigh_"+(ifile+1), "#Delta #theta (deg) (Z-, #theta high) cut", "Counts", 100, -0.5, 0.5);
    hi_delta_theta_Zminus_Thetahigh.setLineColor(ifile+1);
    dgMC_ZTheta_grid.addDataSet(hi_delta_theta_Zminus_Thetahigh, 5);

    H1F hi_delta_theta_Zplus_Thetalow = new H1F("hi_delta_theta_Zplus_Thetalow_"+(ifile+1), "#Delta #theta (deg) (Z+, #theta low) cut", "Counts", 100, -0.5, 0.5);
    hi_delta_theta_Zplus_Thetalow.setLineColor(ifile+1);
    dgMC_ZTheta_grid.addDataSet(hi_delta_theta_Zplus_Thetalow, 6);

    H1F hi_delta_theta_Zplus_Thetahigh = new H1F("hi_delta_theta_Zplus_Thetahigh_"+(ifile+1), "#Delta #theta (deg) (Z+, #theta high) cut", "Counts", 100, -0.5, 0.5);
    hi_delta_theta_Zplus_Thetahigh.setLineColor(ifile+1);
    dgMC_ZTheta_grid.addDataSet(hi_delta_theta_Zplus_Thetahigh, 7);
}

DataGroup dgMC_ZTheta_grid_1 = new DataGroup(4,2);
for(int ifile=0; ifile<2; ifile++) {
    H1F hi_delta_phi_Zminus_Thetalow = new H1F("hi_delta_phi_Zminus_Thetalow_"+(ifile+1), "#Delta #phi (deg) (Z-, #theta low) cut", "Counts", 100, -1.5, 1.5);
    hi_delta_phi_Zminus_Thetalow.setLineColor(ifile+1);
    dgMC_ZTheta_grid_1.addDataSet(hi_delta_phi_Zminus_Thetalow, 0);
    
    H1F hi_delta_phi_Zminus_Thetahigh = new H1F("hi_delta_phi_Zminus_Thetahigh_"+(ifile+1), "#Delta #phi (deg) (Z-, #theta high) cut", "Counts", 100, -1.5, 1.5);
    hi_delta_phi_Zminus_Thetahigh.setLineColor(ifile+1);
    dgMC_ZTheta_grid_1.addDataSet(hi_delta_phi_Zminus_Thetahigh, 1);
   
    H1F hi_delta_phi_Zplus_Thetalow = new H1F("hi_delta_phi_Zplus_Thetalow_"+(ifile+1), "#Delta #phi (deg) (Z+, #theta low) cut", "Counts", 100, -1.5, 1.5);
    hi_delta_phi_Zplus_Thetalow.setLineColor(ifile+1);
    dgMC_ZTheta_grid_1.addDataSet(hi_delta_phi_Zplus_Thetalow, 2);
    
    H1F hi_delta_phi_Zplus_Thetahigh = new H1F("hi_delta_phi_Zplus_Thetahigh_"+(ifile+1), "#Delta #phi (deg) (Z+, #theta high) cut", "Counts", 100, -1.5, 1.5);
    hi_delta_phi_Zplus_Thetahigh.setLineColor(ifile+1);
    dgMC_ZTheta_grid_1.addDataSet(hi_delta_phi_Zplus_Thetahigh, 3);
    

    

    H1F hi_delta_zvertex_Zminus_Thetalow = new H1F("hi_delta_zvertex_Zminus_Thetalow_"+(ifile+1), "#Delta Zvertex (cm) (Z-, #theta low) cut", "Counts", 100, -2.0, 2.0);
    hi_delta_zvertex_Zminus_Thetalow.setLineColor(ifile+1);
    dgMC_ZTheta_grid_1.addDataSet(hi_delta_zvertex_Zminus_Thetalow, 4);
    
    H1F hi_delta_zvertex_Zminus_Thetahigh = new H1F("hi_delta_zvertex_Zminus_Thetahigh_"+(ifile+1), "#Delta Zvertex (cm) (Z-, #theta high) cut", "Counts", 100, -2.0, 2.0);
    hi_delta_zvertex_Zminus_Thetahigh.setLineColor(ifile+1);
    dgMC_ZTheta_grid_1.addDataSet(hi_delta_zvertex_Zminus_Thetahigh, 5);

    H1F hi_delta_zvertex_Zplus_Thetalow = new H1F("hi_delta_zvertex_Zplus_Thetalow_"+(ifile+1), "#Delta Zvertex (cm) (Z+, #theta low) cut", "Counts", 100, -2.0, 2.0);
    hi_delta_zvertex_Zplus_Thetalow.setLineColor(ifile+1);
    dgMC_ZTheta_grid_1.addDataSet(hi_delta_zvertex_Zplus_Thetalow, 6);

    H1F hi_delta_zvertex_Zplus_Thetahigh = new H1F("hi_delta_zvertex_Zplus_Thetahigh_"+(ifile+1), "#Delta Zvertex (cm) (Z+, #theta high) cut", "Counts", 100, -2.0, 2.0);
    hi_delta_zvertex_Zplus_Thetahigh.setLineColor(ifile+1);
    dgMC_ZTheta_grid_1.addDataSet(hi_delta_zvertex_Zplus_Thetahigh, 7);
}






for(int ifile=0; ifile<2; ifile++) {
   String  inputFile = args[ifile];
   HipoDataSource reader = new HipoDataSource();
   reader.open(inputFile);

int RECrows = 0;
int MCrows = 0;

int n_entries_REC_Zminus_Thetalow = 0;
int n_entries_REC_Zminus_Thetahigh = 0;
int n_entries_REC_Zplus_Thetalow = 0;
int n_entries_REC_Zplus_Thetahigh = 0;

int n_entries_deltaP_Zminus_Thetalow = 0;
int n_entries_deltaTheta_Zminus_Thetalow = 0;
int n_entries_deltaPhi_Zminus_Thetalow = 0;
int n_entries_deltaZv_Zminus_Thetalow = 0;

int n_entries_deltaP_Zminus_Thetahigh = 0;
int n_entries_deltaTheta_Zminus_Thetahigh = 0;
int n_entries_deltaPhi_Zminus_Thetahigh = 0;
int n_entries_deltaZv_Zminus_Thetahigh = 0;

int n_entries_deltaP_Zplus_Thetalow = 0;
int n_entries_deltaTheta_Zplus_Thetalow = 0;
int n_entries_deltaPhi_Zplus_Thetalow = 0;
int n_entries_deltaZv_Zplus_Thetalow = 0;

int n_entries_deltaP_Zplus_Thetahigh = 0;
int n_entries_deltaTheta_Zplus_Thetahigh = 0;
int n_entries_deltaPhi_Zplus_Thetahigh = 0;
int n_entries_deltaZv_Zplus_Thetahigh = 0;





   while(reader.hasEvent()){
	DataEvent event = reader.getNextEvent();
	Particle partGenNeg = null;
	Particle partRecNeg = null;

	if(event.hasBank("MC::Particle")==true){
	    DataBank genBank = event.getBank("MC::Particle");
	     int nrows = genBank.rows();
		MCrows = MCrows+nrows;
		//System.out.println("MCrows number is " + nrows);
	      for(int loop=0; loop<nrows; loop++) {
  	          Particle genPart = new Particle(
					       genBank.getInt("pid", loop),
	                                       genBank.getFloat("px", loop),
                                               genBank.getFloat("py", loop),
                                               genBank.getFloat("pz", loop),
                                               genBank.getFloat("vx", loop),
                                               genBank.getFloat("vy", loop),
                                               genBank.getFloat("vz", loop));

			if(genPart.charge()==-1  && partGenNeg==null && genPart.theta()!=0) partGenNeg=genPart;
			dgMC.getH1F("hi_gen_p_"+(ifile+1)).fill(genPart.p());
	             	dgMC.getH1F("hi_gen_theta_"+(ifile+1)).fill(Math.toDegrees(genPart.theta()));
	             	dgMC.getH1F("hi_gen_phi_"+(ifile+1)).fill(Math.toDegrees(genPart.phi()));
		     	dgMC.getH1F("hi_zvertex_gen_"+(ifile+1)).fill(genPart.vz());

			
	       }
	} 

	if(event.hasBank("TimeBasedTrkg::TBTracks")==true){
	      DataBank  bank = event.getBank("TimeBasedTrkg::TBTracks");
	      int rows = bank.rows();
		RECrows = RECrows+rows;
		//System.out.println("RECrows number is " + rows);
	      for(int loop=0; loop<rows; loop++) {
	          	int pidCode=0;
                	if(bank.getByte("q", loop)==-1) pidCode = 11;
			else if(bank.getByte("q", loop)==1) pidCode = 211;
			else pidCode = 22;
  	          Particle recParticle = new Particle(
                                          pidCode,
                                          bank.getFloat("p0_x", loop),
                                          bank.getFloat("p0_y", loop),
                                          bank.getFloat("p0_z", loop),
                                          bank.getFloat("Vtx0_x", loop),
                                          bank.getFloat("Vtx0_y", loop),
                                          bank.getFloat("Vtx0_z", loop));

		  	recParticle.setProperty("ndf", (float) bank.getShort("ndf", loop));
			
			if(recParticle.charge()<0 && recParticle.getProperty("ndf")>0) {	      
	             			dgMC.getH1F("hi_rec_p_"+(ifile+1)).fill(recParticle.p());
	             			dgMC.getH1F("hi_rec_theta_"+(ifile+1)).fill(Math.toDegrees(recParticle.theta()));
	             			dgMC.getH1F("hi_rec_phi_"+(ifile+1)).fill(Math.toDegrees(recParticle.phi()));
		     			dgMC.getH1F("hi_zvertex_neg_"+(ifile+1)).fill(recParticle.vz());
		  	}
		
			if(partRecNeg==null && recParticle.charge()<0) partRecNeg=recParticle;
		
	       }
		

 	}
	
	if(partGenNeg != null && partRecNeg != null) {
	double deltaP = (partRecNeg.p()-partGenNeg.p())/partGenNeg.p();
	
	double deltaTheta = (Math.toDegrees(partRecNeg.theta()-partGenNeg.theta()));
	double deltaPhi = (Math.toDegrees(partRecNeg.phi()-partGenNeg.phi()));
	double deltaZv = (partRecNeg.vz()-partGenNeg.vz());
		
		dgMC.getH1F("hi_delta_p_"+(ifile+1)).fill(deltaP);
		dgMC.getH1F("hi_delta_theta_"+(ifile+1)).fill(deltaTheta);
		dgMC.getH1F("hi_delta_phi_"+(ifile+1)).fill(deltaPhi);
		dgMC.getH1F("hi_delta_zvertex_"+(ifile+1)).fill(deltaZv);
	}
	
	if( partGenNeg!=null && partRecNeg!=null && Math.toDegrees(partGenNeg.theta())>=5.0 && Math.toDegrees(partGenNeg.theta())<15.0 && partGenNeg.vz()<0 && partGenNeg.vz()>=-20.0) {
	//System.out.println(partRecNeg.p() + " " + partGenNeg.p());
	double deltaP = (partRecNeg.p()-partGenNeg.p())/partGenNeg.p();
	//System.out.println(deltaP + " delta P ");
	double deltaTheta = (Math.toDegrees(partRecNeg.theta()-partGenNeg.theta()));
	//System.out.println(deltaTheta + " delta Theta ");
	double deltaPhi = (Math.toDegrees(partRecNeg.phi()-partGenNeg.phi()));
	//System.out.println(deltaPhi + " delta Phi ");
	double deltaZv = (partRecNeg.vz()-partGenNeg.vz());
		
		if (deltaP < -0.008 || deltaP > 0.004){
		dgMC_ZTheta_grid.getH1F("hi_delta_p_Zminus_Thetalow_"+(ifile+1)).fill(deltaP);
		n_entries_deltaP_Zminus_Thetalow = n_entries_deltaP_Zminus_Thetalow+1;
		}
		if (deltaTheta < -0.06 || deltaTheta > 0.09){
		dgMC_ZTheta_grid.getH1F("hi_delta_theta_Zminus_Thetalow_"+(ifile+1)).fill(deltaTheta);
		n_entries_deltaTheta_Zminus_Thetalow = n_entries_deltaTheta_Zminus_Thetalow+1;
		}
		if (deltaPhi < -0.4 || deltaPhi > 0.4){
		dgMC_ZTheta_grid_1.getH1F("hi_delta_phi_Zminus_Thetalow_"+(ifile+1)).fill(deltaPhi);
		n_entries_deltaPhi_Zminus_Thetalow = n_entries_deltaPhi_Zminus_Thetalow+1;
		}
		if (deltaZv < -0.82 || deltaZv > 0.75){
		dgMC_ZTheta_grid_1.getH1F("hi_delta_zvertex_Zminus_Thetalow_"+(ifile+1)).fill(deltaZv); 
		n_entries_deltaZv_Zminus_Thetalow = n_entries_deltaZv_Zminus_Thetalow+1;
		}
		n_entries_REC_Zminus_Thetalow = n_entries_REC_Zminus_Thetalow+1;	
	}
	//System.out.println(" (Z-, theta low) loop done! ");
	//System.out.println(" Fit started: ");
	//fitMC(dgMC_ZTheta_grid.getH1F("hi_delta_p_Zminus_Thetalow_"+(ifile+1)), dgMC_ZTheta_grid.getF1D("f1_dp_neg_"+(ifile+1)));
	//System.out.println(" Fit ended! ");

	if( partGenNeg!=null && partRecNeg!=null && Math.toDegrees(partGenNeg.theta())>=15.0 && Math.toDegrees(partGenNeg.theta())<=45.0 && partGenNeg.vz()<0 && partGenNeg.vz()>=-20.0) {
	//System.out.println(partRecNeg.p() + " " + partGenNeg.p());
	double deltaP = (partRecNeg.p()-partGenNeg.p())/partGenNeg.p();
	//System.out.println(deltaP + " delta P ");
	double deltaTheta = (Math.toDegrees(partRecNeg.theta()-partGenNeg.theta()));
	//System.out.println(deltaTheta + " delta Theta ");
	double deltaPhi = (Math.toDegrees(partRecNeg.phi()-partGenNeg.phi()));
	//System.out.println(deltaPhi + " delta Phi ");
	double deltaZv = (partRecNeg.vz()-partGenNeg.vz());
	//System.out.println(deltaZv + " delta Zv ");
		if (deltaP < -0.016 || deltaP > 0.008){
		dgMC_ZTheta_grid.getH1F("hi_delta_p_Zminus_Thetahigh_"+(ifile+1)).fill(deltaP);
		n_entries_deltaP_Zminus_Thetahigh = n_entries_deltaP_Zminus_Thetahigh+1;
		}
		if (deltaTheta < -0.11 || deltaTheta > 0.15){
		dgMC_ZTheta_grid.getH1F("hi_delta_theta_Zminus_Thetahigh_"+(ifile+1)).fill(deltaTheta);
		n_entries_deltaTheta_Zminus_Thetahigh = n_entries_deltaTheta_Zminus_Thetahigh+1;
		}
		if (deltaPhi < -0.43 || deltaPhi > 0.50){
		dgMC_ZTheta_grid_1.getH1F("hi_delta_phi_Zminus_Thetahigh_"+(ifile+1)).fill(deltaPhi);
		n_entries_deltaPhi_Zminus_Thetahigh = n_entries_deltaPhi_Zminus_Thetahigh+1;
		}
		if (deltaZv < -0.82 || deltaZv > 0.84){
		dgMC_ZTheta_grid_1.getH1F("hi_delta_zvertex_Zminus_Thetahigh_"+(ifile+1)).fill(deltaZv); 
		n_entries_deltaZv_Zminus_Thetahigh = n_entries_deltaZv_Zminus_Thetahigh+1;
		}	

		n_entries_REC_Zminus_Thetahigh = n_entries_REC_Zminus_Thetahigh+1;
	}
	//System.out.println(" (Z-, theta high) loop done! ");

	if( partGenNeg!=null && partRecNeg!=null && Math.toDegrees(partGenNeg.theta())>=5.0 && Math.toDegrees(partGenNeg.theta())<15.0 && partGenNeg.vz()>=0 && partGenNeg.vz()<=20.0) {
	double deltaP = (partRecNeg.p()-partGenNeg.p())/partGenNeg.p();
	double deltaTheta = (Math.toDegrees(partRecNeg.theta()-partGenNeg.theta()));
	double deltaPhi = (Math.toDegrees(partRecNeg.phi()-partGenNeg.phi()));
	double deltaZv = (partRecNeg.vz()-partGenNeg.vz());
		if (deltaP < -0.007 || deltaP > 0.005){
		dgMC_ZTheta_grid.getH1F("hi_delta_p_Zplus_Thetalow_"+(ifile+1)).fill(deltaP);
		n_entries_deltaP_Zplus_Thetalow = n_entries_deltaP_Zplus_Thetalow+1;
		}
		if (deltaTheta < -0.052 || deltaTheta > 0.071){
		dgMC_ZTheta_grid.getH1F("hi_delta_theta_Zplus_Thetalow_"+(ifile+1)).fill(deltaTheta);
		n_entries_deltaTheta_Zplus_Thetalow = n_entries_deltaTheta_Zplus_Thetalow+1;
		}
		if (deltaPhi < -0.36 || deltaPhi > 0.35){
		dgMC_ZTheta_grid_1.getH1F("hi_delta_phi_Zplus_Thetalow_"+(ifile+1)).fill(deltaPhi);
		n_entries_deltaPhi_Zplus_Thetalow = n_entries_deltaPhi_Zplus_Thetalow+1;
		}
		if (deltaZv < -0.78 || deltaZv > 0.68){
		dgMC_ZTheta_grid_1.getH1F("hi_delta_zvertex_Zplus_Thetalow_"+(ifile+1)).fill(deltaZv); 	
		n_entries_deltaZv_Zplus_Thetalow = n_entries_deltaZv_Zplus_Thetalow+1;
		}

		n_entries_REC_Zplus_Thetalow = n_entries_REC_Zplus_Thetalow+1;
	}
	//System.out.println(" (Z+, theta low) loop done! ");

	if( partGenNeg!=null && partRecNeg!=null && Math.toDegrees(partGenNeg.theta())>=15.0 && Math.toDegrees(partGenNeg.theta())<=45.0 && partGenNeg.vz()>=0 && partGenNeg.vz()<=20.0) {
	double deltaP = (partRecNeg.p()-partGenNeg.p())/partGenNeg.p();
	double deltaTheta = (Math.toDegrees(partRecNeg.theta()-partGenNeg.theta()));
	double deltaPhi = (Math.toDegrees(partRecNeg.phi()-partGenNeg.phi()));
	double deltaZv = (partRecNeg.vz()-partGenNeg.vz());
		if (deltaP < -0.012 || deltaP > 0.008){
		dgMC_ZTheta_grid.getH1F("hi_delta_p_Zplus_Thetahigh_"+(ifile+1)).fill(deltaP);
		n_entries_deltaP_Zplus_Thetahigh = n_entries_deltaP_Zplus_Thetahigh+1;
		}
		if (deltaTheta < -0.086 || deltaTheta > 0.114){
		dgMC_ZTheta_grid.getH1F("hi_delta_theta_Zplus_Thetahigh_"+(ifile+1)).fill(deltaTheta);
		n_entries_deltaTheta_Zplus_Thetahigh = n_entries_deltaTheta_Zplus_Thetahigh+1;
		}
		if (deltaPhi < -0.354 || deltaPhi > 0.34){
		dgMC_ZTheta_grid_1.getH1F("hi_delta_phi_Zplus_Thetahigh_"+(ifile+1)).fill(deltaPhi);
		n_entries_deltaPhi_Zplus_Thetahigh = n_entries_deltaPhi_Zplus_Thetahigh+1;
		}
		if (deltaZv < -0.77 || deltaZv > 0.75){
		dgMC_ZTheta_grid_1.getH1F("hi_delta_zvertex_Zplus_Thetahigh_"+(ifile+1)).fill(deltaZv); 
		n_entries_deltaZv_Zplus_Thetahigh = n_entries_deltaZv_Zplus_Thetahigh+1;
		}	

		n_entries_REC_Zplus_Thetahigh = n_entries_REC_Zplus_Thetahigh+1;
	}
	//System.out.println(" (Z+, theta high) loop done! ");

}
   reader.close();
System.out.println("RECrows total number is " + RECrows);
System.out.println("MCrows total number is " + MCrows);

System.out.println("n_entries, Zminus_Thetalow cut, is " + n_entries_REC_Zminus_Thetalow);
System.out.println("n_entries, DeltaP, Zminus_Thetalow cut, outside +-2sigma, is: " + n_entries_deltaP_Zminus_Thetalow);
System.out.println("n_entries, DeltaTheta, Zminus_Thetalow cut, outside +-2sigma, is: " + n_entries_deltaTheta_Zminus_Thetalow);
System.out.println("n_entries, DeltaPhi, Zminus_Thetalow cut, outside +-2sigma, is: " + n_entries_deltaPhi_Zminus_Thetalow);
System.out.println("n_entries, DeltaZv, Zminus_Thetalow cut, outside +-2sigma, is: " + n_entries_deltaZv_Zminus_Thetalow);


System.out.println("n_entries, Zminus_Thetahigh cut, is " + n_entries_REC_Zminus_Thetahigh);
System.out.println("n_entries, DeltaP, Zminus_Thetahigh cut, outside +-2sigma, is: " + n_entries_deltaP_Zminus_Thetahigh);
System.out.println("n_entries, DeltaTheta, Zminus_Thetahigh cut, outside +-2sigma, is: " + n_entries_deltaTheta_Zminus_Thetahigh);
System.out.println("n_entries, DeltaPhi, Zminus_Thetahigh cut, outside +-2sigma, is: " + n_entries_deltaPhi_Zminus_Thetahigh);
System.out.println("n_entries, DeltaZv, Zminus_Thetahigh cut, outside +-2sigma, is: " + n_entries_deltaZv_Zminus_Thetahigh);


System.out.println("n_entries, Zplus_Thetalow cut, is " + n_entries_REC_Zplus_Thetalow);
System.out.println("n_entries, DeltaP, Zplus_Thetalow cut, outside +-2sigma, is: " + n_entries_deltaP_Zplus_Thetalow);
System.out.println("n_entries, DeltaTheta, Zplus_Thetalow cut, outside +-2sigma, is: " + n_entries_deltaTheta_Zplus_Thetalow);
System.out.println("n_entries, DeltaPhi, Zplus_Thetalow cut, outside +-2sigma, is: " + n_entries_deltaPhi_Zplus_Thetalow);
System.out.println("n_entries, DeltaZv, Zplus_Thetalow cut, outside +-2sigma, is: " + n_entries_deltaZv_Zplus_Thetalow);


System.out.println("n_entries, Zplus_Thetahigh cut, is " + n_entries_REC_Zplus_Thetahigh);
System.out.println("n_entries, DeltaP, Zplus_Thetahigh cut, outside +-2sigma, is: " + n_entries_deltaP_Zplus_Thetahigh);
System.out.println("n_entries, DeltaTheta, Zplus_Thetahigh cut, outside +-2sigma, is: " + n_entries_deltaTheta_Zplus_Thetahigh);
System.out.println("n_entries, DeltaPhi, Zplus_Thetahigh cut, outside +-2sigma, is: " + n_entries_deltaPhi_Zplus_Thetahigh);
System.out.println("n_entries, DeltaZv, Zplus_Thetalhigh cut, outside +-2sigma, is: " + n_entries_deltaZv_Zplus_Thetahigh);

}

EmbeddedCanvasTabbed mcCanvas = new EmbeddedCanvasTabbed("MC","MC_ZTheta_grid","MC_ZTheta_grid_1");

mcCanvas.getCanvas("MC").draw(dgMC);
mcCanvas.getCanvas("MC").setGridX(false);
mcCanvas.getCanvas("MC").setGridY(false);
mcCanvas.getCanvas("MC").setAxisFontSize(18);
mcCanvas.getCanvas("MC").setAxisTitleSize(24);

mcCanvas.getCanvas("MC_ZTheta_grid").draw(dgMC_ZTheta_grid); 
//mcCanvas.getCanvas("MC_ZTheta_grid").draw(dgMC_ZTheta_grid.getF1D("f1_dp_neg"),"same");
mcCanvas.getCanvas("MC_ZTheta_grid").setGridX(false);
mcCanvas.getCanvas("MC_ZTheta_grid").setGridY(false);
mcCanvas.getCanvas("MC_ZTheta_grid").setAxisFontSize(18);
mcCanvas.getCanvas("MC_ZTheta_grid").setAxisTitleSize(24);

mcCanvas.getCanvas("MC_ZTheta_grid_1").draw(dgMC_ZTheta_grid_1); 
mcCanvas.getCanvas("MC_ZTheta_grid_1").setGridX(false);
mcCanvas.getCanvas("MC_ZTheta_grid_1").setGridY(false);
mcCanvas.getCanvas("MC_ZTheta_grid_1").setAxisFontSize(18);
mcCanvas.getCanvas("MC_ZTheta_grid_1").setAxisTitleSize(24);

JFrame frame = new JFrame("MC");
frame.setSize(1200, 1000);
frame.add(mcCanvas);
frame.setLocationRelativeTo(null);
frame.setVisible(true);
