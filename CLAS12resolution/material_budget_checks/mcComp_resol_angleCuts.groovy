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
    F1D f1 = new F1D("f1_"+(ifile+1),"[amp]*gaus(x,[mean],[sigma])", -0.1, 0.1);
    f1.setParameter(0, 0);
    f1.setParameter(1, 0);
    f1.setParameter(2, 1.0);
    f1.setLineWidth(2);
    f1.setLineColor(2);
    f1.setOptStat("1111");
    dgMC_ZTheta_grid.addDataSet(f1, 0);

    H1F hi_delta_p_Zminus_Thetahigh = new H1F("hi_delta_p_Zminus_Thetahigh_"+(ifile+1), "#Delta P / p(GeV) (Z-, #theta high) cut", "Counts", 100, -0.1, 0.1);
    hi_delta_p_Zminus_Thetahigh.setLineColor(ifile+1);
    dgMC_ZTheta_grid.addDataSet(hi_delta_p_Zminus_Thetahigh, 1);
    F1D f2 = new F1D("f2_"+(ifile+1),"[amp]*gaus(x,[mean],[sigma])", -0.1, 0.1);
    f2.setParameter(0, 0);
    f2.setParameter(1, 0);
    f2.setParameter(2, 1.0);
    f2.setLineWidth(2);
    f2.setLineColor(2);
    f2.setOptStat("1111");
    dgMC_ZTheta_grid.addDataSet(f2, 1);
    

    H1F hi_delta_p_Zplus_Thetalow = new H1F("hi_delta_p_Zplus_Thetalow_"+(ifile+1), "#Delta P / p(GeV) (Z+, #theta low) cut", "Counts", 100, -0.1, 0.1);
    hi_delta_p_Zplus_Thetalow.setLineColor(ifile+1);
    dgMC_ZTheta_grid.addDataSet(hi_delta_p_Zplus_Thetalow, 2);
    F1D f3 = new F1D("f3_"+(ifile+1),"[amp]*gaus(x,[mean],[sigma])", -0.1, 0.1);
    f3.setParameter(0, 0);
    f3.setParameter(1, 0);
    f3.setParameter(2, 1.0);
    f3.setLineWidth(2);
    f3.setLineColor(2);
    f3.setOptStat("1111");
    dgMC_ZTheta_grid.addDataSet(f3, 2);

    H1F hi_delta_p_Zplus_Thetahigh = new H1F("hi_delta_p_Zplus_Thetahigh_"+(ifile+1), "#Delta P / p(GeV) (Z+, #theta high) cut", "Counts", 100, -0.1, 0.1);
    hi_delta_p_Zplus_Thetahigh.setLineColor(ifile+1);
    dgMC_ZTheta_grid.addDataSet(hi_delta_p_Zplus_Thetahigh, 3);
    F1D f4 = new F1D("f4_"+(ifile+1),"[amp]*gaus(x,[mean],[sigma])", -0.1, 0.1);
    f4.setParameter(0, 0);
    f4.setParameter(1, 0);
    f4.setParameter(2, 1.0);
    f4.setLineWidth(2);
    f4.setLineColor(2);
    f4.setOptStat("1111");
    dgMC_ZTheta_grid.addDataSet(f4, 3);



    H1F hi_delta_theta_Zminus_Thetalow = new H1F("hi_delta_theta_Zminus_Thetalow_"+(ifile+1), "#Delta #theta (deg) (Z-, #theta low) cut", "Counts", 100, -0.5, 0.5);
    hi_delta_theta_Zminus_Thetalow.setLineColor(ifile+1);
    dgMC_ZTheta_grid.addDataSet(hi_delta_theta_Zminus_Thetalow, 4);
    F1D f5 = new F1D("f5_"+(ifile+1),"[amp]*gaus(x,[mean],[sigma])", -0.1, 0.1);
    f5.setParameter(0, 0);
    f5.setParameter(1, 0);
    f5.setParameter(2, 1.0);
    f5.setLineWidth(2);
    f5.setLineColor(2);
    f5.setOptStat("1111");
    dgMC_ZTheta_grid.addDataSet(f5, 4);

    H1F hi_delta_theta_Zminus_Thetahigh = new H1F("hi_delta_theta_Zminus_Thetahigh_"+(ifile+1), "#Delta #theta (deg) (Z-, #theta high) cut", "Counts", 100, -0.5, 0.5);
    hi_delta_theta_Zminus_Thetahigh.setLineColor(ifile+1);
    dgMC_ZTheta_grid.addDataSet(hi_delta_theta_Zminus_Thetahigh, 5);
    F1D f6 = new F1D("f6_"+(ifile+1),"[amp]*gaus(x,[mean],[sigma])", -0.1, 0.1);
    f6.setParameter(0, 0);
    f6.setParameter(1, 0);
    f6.setParameter(2, 1.0);
    f6.setLineWidth(2);
    f6.setLineColor(2);
    f6.setOptStat("1111");
    dgMC_ZTheta_grid.addDataSet(f6, 5);
    

    H1F hi_delta_theta_Zplus_Thetalow = new H1F("hi_delta_theta_Zplus_Thetalow_"+(ifile+1), "#Delta #theta (deg) (Z+, #theta low) cut", "Counts", 100, -0.5, 0.5);
    hi_delta_theta_Zplus_Thetalow.setLineColor(ifile+1);
    dgMC_ZTheta_grid.addDataSet(hi_delta_theta_Zplus_Thetalow, 6);
    F1D f7 = new F1D("f7_"+(ifile+1),"[amp]*gaus(x,[mean],[sigma])", -0.1, 0.1);
    f7.setParameter(0, 0);
    f7.setParameter(1, 0);
    f7.setParameter(2, 1.0);
    f7.setLineWidth(2);
    f7.setLineColor(2);
    f7.setOptStat("1111");
    dgMC_ZTheta_grid.addDataSet(f7, 6);

    H1F hi_delta_theta_Zplus_Thetahigh = new H1F("hi_delta_theta_Zplus_Thetahigh_"+(ifile+1), "#Delta #theta (deg) (Z+, #theta high) cut", "Counts", 100, -0.5, 0.5);
    hi_delta_theta_Zplus_Thetahigh.setLineColor(ifile+1);
    dgMC_ZTheta_grid.addDataSet(hi_delta_theta_Zplus_Thetahigh, 7);
    F1D f8 = new F1D("f8_"+(ifile+1),"[amp]*gaus(x,[mean],[sigma])", -0.1, 0.1);
    f8.setParameter(0, 0);
    f8.setParameter(1, 0);
    f8.setParameter(2, 1.0);
    f8.setLineWidth(2);
    f8.setLineColor(2);
    f8.setOptStat("1111");
    dgMC_ZTheta_grid.addDataSet(f8, 7);

}

DataGroup dgMC_ZTheta_grid_1 = new DataGroup(4,2);
for(int ifile=0; ifile<2; ifile++) {
    H1F hi_delta_phi_Zminus_Thetalow = new H1F("hi_delta_phi_Zminus_Thetalow_"+(ifile+1), "#Delta #phi (deg) (Z-, #theta low) cut", "Counts", 100, -1.5, 1.5);
    hi_delta_phi_Zminus_Thetalow.setLineColor(ifile+1);
    dgMC_ZTheta_grid_1.addDataSet(hi_delta_phi_Zminus_Thetalow, 0);
    F1D f1_1 = new F1D("f1_1_"+(ifile+1),"[amp]*gaus(x,[mean],[sigma])", -0.1, 0.1);
    f1_1.setParameter(0, 0);
    f1_1.setParameter(1, 0);
    f1_1.setParameter(2, 1.0);
    f1_1.setLineWidth(2);
    f1_1.setLineColor(2);
    f1_1.setOptStat("1111");
    dgMC_ZTheta_grid_1.addDataSet(f1_1, 0);

    H1F hi_delta_phi_Zminus_Thetahigh = new H1F("hi_delta_phi_Zminus_Thetahigh_"+(ifile+1), "#Delta #phi (deg) (Z-, #theta high) cut", "Counts", 100, -1.5, 1.5);
    hi_delta_phi_Zminus_Thetahigh.setLineColor(ifile+1);
    dgMC_ZTheta_grid_1.addDataSet(hi_delta_phi_Zminus_Thetahigh, 1);
    F1D f2_1 = new F1D("f2_1_"+(ifile+1),"[amp]*gaus(x,[mean],[sigma])", -0.1, 0.1);
    f2_1.setParameter(0, 0);
    f2_1.setParameter(1, 0);
    f2_1.setParameter(2, 1.0);
    f2_1.setLineWidth(2);
    f2_1.setLineColor(2);
    f2_1.setOptStat("1111");
    dgMC_ZTheta_grid_1.addDataSet(f2_1, 1);

    H1F hi_delta_phi_Zplus_Thetalow = new H1F("hi_delta_phi_Zplus_Thetalow_"+(ifile+1), "#Delta #phi (deg) (Z+, #theta low) cut", "Counts", 100, -1.5, 1.5);
    hi_delta_phi_Zplus_Thetalow.setLineColor(ifile+1);
    dgMC_ZTheta_grid_1.addDataSet(hi_delta_phi_Zplus_Thetalow, 2);
    F1D f3_1 = new F1D("f3_1_"+(ifile+1),"[amp]*gaus(x,[mean],[sigma])", -0.1, 0.1);
    f3_1.setParameter(0, 0);
    f3_1.setParameter(1, 0);
    f3_1.setParameter(2, 1.0);
    f3_1.setLineWidth(2);
    f3_1.setLineColor(2);
    f3_1.setOptStat("1111");
    dgMC_ZTheta_grid_1.addDataSet(f3_1, 2);

    H1F hi_delta_phi_Zplus_Thetahigh = new H1F("hi_delta_phi_Zplus_Thetahigh_"+(ifile+1), "#Delta #phi (deg) (Z+, #theta high) cut", "Counts", 100, -1.5, 1.5);
    hi_delta_phi_Zplus_Thetahigh.setLineColor(ifile+1);
    dgMC_ZTheta_grid_1.addDataSet(hi_delta_phi_Zplus_Thetahigh, 3);
    F1D f4_1 = new F1D("f4_1_"+(ifile+1),"[amp]*gaus(x,[mean],[sigma])", -0.1, 0.1);
    f4_1.setParameter(0, 0);
    f4_1.setParameter(1, 0);
    f4_1.setParameter(2, 1.0);
    f4_1.setLineWidth(2);
    f4_1.setLineColor(2);
    f4_1.setOptStat("1111");
    dgMC_ZTheta_grid_1.addDataSet(f4_1, 3);

    

    H1F hi_delta_zvertex_Zminus_Thetalow = new H1F("hi_delta_zvertex_Zminus_Thetalow_"+(ifile+1), "#Delta Zvertex (cm) (Z-, #theta low) cut", "Counts", 100, -2.0, 2.0);
    hi_delta_zvertex_Zminus_Thetalow.setLineColor(ifile+1);
    dgMC_ZTheta_grid_1.addDataSet(hi_delta_zvertex_Zminus_Thetalow, 4);
    F1D f5_1 = new F1D("f5_1_"+(ifile+1),"[amp]*gaus(x,[mean],[sigma])", -0.1, 0.1);
    f5_1.setParameter(0, 0);
    f5_1.setParameter(1, 0);
    f5_1.setParameter(2, 1.0);
    f5_1.setLineWidth(2);
    f5_1.setLineColor(2);
    f5_1.setOptStat("1111");
    dgMC_ZTheta_grid_1.addDataSet(f5_1, 4);

    H1F hi_delta_zvertex_Zminus_Thetahigh = new H1F("hi_delta_zvertex_Zminus_Thetahigh_"+(ifile+1), "#Delta Zvertex (cm) (Z-, #theta high) cut", "Counts", 100, -2.0, 2.0);
    hi_delta_zvertex_Zminus_Thetahigh.setLineColor(ifile+1);
    dgMC_ZTheta_grid_1.addDataSet(hi_delta_zvertex_Zminus_Thetahigh, 5);
    F1D f6_1 = new F1D("f6_1_"+(ifile+1),"[amp]*gaus(x,[mean],[sigma])", -0.1, 0.1);
    f6_1.setParameter(0, 0);
    f6_1.setParameter(1, 0);
    f6_1.setParameter(2, 1.0);
    f6_1.setLineWidth(2);
    f6_1.setLineColor(2);
    f6_1.setOptStat("1111");
    dgMC_ZTheta_grid_1.addDataSet(f6_1, 5);

    H1F hi_delta_zvertex_Zplus_Thetalow = new H1F("hi_delta_zvertex_Zplus_Thetalow_"+(ifile+1), "#Delta Zvertex (cm) (Z+, #theta low) cut", "Counts", 100, -2.0, 2.0);
    hi_delta_zvertex_Zplus_Thetalow.setLineColor(ifile+1);
    dgMC_ZTheta_grid_1.addDataSet(hi_delta_zvertex_Zplus_Thetalow, 6);
    F1D f7_1 = new F1D("f7_1_"+(ifile+1),"[amp]*gaus(x,[mean],[sigma])", -0.1, 0.1);
    f7_1.setParameter(0, 0);
    f7_1.setParameter(1, 0);
    f7_1.setParameter(2, 1.0);
    f7_1.setLineWidth(2);
    f7_1.setLineColor(2);
    f7_1.setOptStat("1111");
    dgMC_ZTheta_grid_1.addDataSet(f7_1, 6);

    H1F hi_delta_zvertex_Zplus_Thetahigh = new H1F("hi_delta_zvertex_Zplus_Thetahigh_"+(ifile+1), "#Delta Zvertex (cm) (Z+, #theta high) cut", "Counts", 100, -2.0, 2.0);
    hi_delta_zvertex_Zplus_Thetahigh.setLineColor(ifile+1);
    dgMC_ZTheta_grid_1.addDataSet(hi_delta_zvertex_Zplus_Thetahigh, 7);
    F1D f8_1 = new F1D("f8_1_"+(ifile+1),"[amp]*gaus(x,[mean],[sigma])", -0.1, 0.1);
    f8_1.setParameter(0, 0);
    f8_1.setParameter(1, 0);
    f8_1.setParameter(2, 1.0);
    f8_1.setLineWidth(2);
    f8_1.setLineColor(2);
    f8_1.setOptStat("1111");
    dgMC_ZTheta_grid_1.addDataSet(f8_1, 7);

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
		
		dgMC_ZTheta_grid.getH1F("hi_delta_p_Zminus_Thetalow_"+(ifile+1)).fill(deltaP);
		//dgMC_ZTheta_grid.getF1D("f1_dp_neg_"+(ifile+1));		
		//fitMC(dgMC_ZTheta_grid.getH1F("hi_delta_p_Zminus_Thetalow_"+(ifile+1)), dgMC_ZTheta_grid.getF1D("f1_dp_neg_"+(ifile+1)));

		dgMC_ZTheta_grid.getH1F("hi_delta_theta_Zminus_Thetalow_"+(ifile+1)).fill(deltaTheta);
		dgMC_ZTheta_grid_1.getH1F("hi_delta_phi_Zminus_Thetalow_"+(ifile+1)).fill(deltaPhi);
		dgMC_ZTheta_grid_1.getH1F("hi_delta_zvertex_Zminus_Thetalow_"+(ifile+1)).fill(deltaZv); 

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
		
		dgMC_ZTheta_grid.getH1F("hi_delta_p_Zminus_Thetahigh_"+(ifile+1)).fill(deltaP);
		dgMC_ZTheta_grid.getH1F("hi_delta_theta_Zminus_Thetahigh_"+(ifile+1)).fill(deltaTheta);
		dgMC_ZTheta_grid_1.getH1F("hi_delta_phi_Zminus_Thetahigh_"+(ifile+1)).fill(deltaPhi);
		dgMC_ZTheta_grid_1.getH1F("hi_delta_zvertex_Zminus_Thetahigh_"+(ifile+1)).fill(deltaZv); 	

		n_entries_REC_Zminus_Thetahigh = n_entries_REC_Zminus_Thetahigh+1;
	}
	//System.out.println(" (Z-, theta high) loop done! ");

	if( partGenNeg!=null && partRecNeg!=null && Math.toDegrees(partGenNeg.theta())>=5.0 && Math.toDegrees(partGenNeg.theta())<15.0 && partGenNeg.vz()>=0 && partGenNeg.vz()<=20.0) {
	double deltaP = (partRecNeg.p()-partGenNeg.p())/partGenNeg.p();
	double deltaTheta = (Math.toDegrees(partRecNeg.theta()-partGenNeg.theta()));
	double deltaPhi = (Math.toDegrees(partRecNeg.phi()-partGenNeg.phi()));
	double deltaZv = (partRecNeg.vz()-partGenNeg.vz());
		
		dgMC_ZTheta_grid.getH1F("hi_delta_p_Zplus_Thetalow_"+(ifile+1)).fill(deltaP);
		
		dgMC_ZTheta_grid.getH1F("hi_delta_theta_Zplus_Thetalow_"+(ifile+1)).fill(deltaTheta);
		dgMC_ZTheta_grid_1.getH1F("hi_delta_phi_Zplus_Thetalow_"+(ifile+1)).fill(deltaPhi);
		dgMC_ZTheta_grid_1.getH1F("hi_delta_zvertex_Zplus_Thetalow_"+(ifile+1)).fill(deltaZv); 	

		n_entries_REC_Zplus_Thetalow = n_entries_REC_Zplus_Thetalow+1;
	}
	//System.out.println(" (Z+, theta low) loop done! ");

	if( partGenNeg!=null && partRecNeg!=null && Math.toDegrees(partGenNeg.theta())>=15.0 && Math.toDegrees(partGenNeg.theta())<=45.0 && partGenNeg.vz()>=0 && partGenNeg.vz()<=20.0) {
	double deltaP = (partRecNeg.p()-partGenNeg.p())/partGenNeg.p();
	double deltaTheta = (Math.toDegrees(partRecNeg.theta()-partGenNeg.theta()));
	double deltaPhi = (Math.toDegrees(partRecNeg.phi()-partGenNeg.phi()));
	double deltaZv = (partRecNeg.vz()-partGenNeg.vz());
		
		dgMC_ZTheta_grid.getH1F("hi_delta_p_Zplus_Thetahigh_"+(ifile+1)).fill(deltaP);
		dgMC_ZTheta_grid.getH1F("hi_delta_theta_Zplus_Thetahigh_"+(ifile+1)).fill(deltaTheta);
		dgMC_ZTheta_grid_1.getH1F("hi_delta_phi_Zplus_Thetahigh_"+(ifile+1)).fill(deltaPhi);
		dgMC_ZTheta_grid_1.getH1F("hi_delta_zvertex_Zplus_Thetahigh_"+(ifile+1)).fill(deltaZv); 	

		n_entries_REC_Zplus_Thetahigh = n_entries_REC_Zplus_Thetahigh+1;
	}
	//System.out.println(" (Z+, theta high) loop done! ");

}
   reader.close();
System.out.println("RECrows total number is " + RECrows);
System.out.println("MCrows total number is " + MCrows);
System.out.println("n_entries, Zminus_Thetalow cut, is " + n_entries_REC_Zminus_Thetalow);
System.out.println("n_entries, Zminus_Thetahigh cut, is " + n_entries_REC_Zminus_Thetahigh);
System.out.println("n_entries, Zplus_Thetalow cut, is " + n_entries_REC_Zplus_Thetalow);
System.out.println("n_entries, Zplus_Thetahigh cut, is " + n_entries_REC_Zplus_Thetahigh);

System.out.println(" Fit started: ");

fitMC(dgMC_ZTheta_grid.getH1F("hi_delta_p_Zminus_Thetalow_"+(ifile+1)), dgMC_ZTheta_grid.getF1D("f1_"+(ifile+1)));
fitMC(dgMC_ZTheta_grid.getH1F("hi_delta_p_Zminus_Thetahigh_"+(ifile+1)), dgMC_ZTheta_grid.getF1D("f2_"+(ifile+1)));
fitMC(dgMC_ZTheta_grid.getH1F("hi_delta_p_Zplus_Thetalow_"+(ifile+1)), dgMC_ZTheta_grid.getF1D("f3_"+(ifile+1)));
fitMC(dgMC_ZTheta_grid.getH1F("hi_delta_p_Zplus_Thetahigh_"+(ifile+1)), dgMC_ZTheta_grid.getF1D("f4_"+(ifile+1)));
fitMC(dgMC_ZTheta_grid.getH1F("hi_delta_theta_Zminus_Thetalow_"+(ifile+1)), dgMC_ZTheta_grid.getF1D("f5_"+(ifile+1)));
fitMC(dgMC_ZTheta_grid.getH1F("hi_delta_theta_Zminus_Thetahigh_"+(ifile+1)), dgMC_ZTheta_grid.getF1D("f6_"+(ifile+1)));
fitMC(dgMC_ZTheta_grid.getH1F("hi_delta_theta_Zplus_Thetalow_"+(ifile+1)), dgMC_ZTheta_grid.getF1D("f7_"+(ifile+1)));
fitMC(dgMC_ZTheta_grid.getH1F("hi_delta_theta_Zplus_Thetahigh_"+(ifile+1)), dgMC_ZTheta_grid.getF1D("f8_"+(ifile+1)));

fitMC(dgMC_ZTheta_grid_1.getH1F("hi_delta_phi_Zminus_Thetalow_"+(ifile+1)), dgMC_ZTheta_grid_1.getF1D("f1_1_"+(ifile+1)));
fitMC(dgMC_ZTheta_grid_1.getH1F("hi_delta_phi_Zminus_Thetahigh_"+(ifile+1)), dgMC_ZTheta_grid_1.getF1D("f2_1_"+(ifile+1)));
fitMC(dgMC_ZTheta_grid_1.getH1F("hi_delta_phi_Zplus_Thetalow_"+(ifile+1)), dgMC_ZTheta_grid_1.getF1D("f3_1_"+(ifile+1)));
fitMC(dgMC_ZTheta_grid_1.getH1F("hi_delta_phi_Zplus_Thetahigh_"+(ifile+1)), dgMC_ZTheta_grid_1.getF1D("f4_1_"+(ifile+1)));
fitMC(dgMC_ZTheta_grid_1.getH1F("hi_delta_zvertex_Zminus_Thetalow_"+(ifile+1)), dgMC_ZTheta_grid_1.getF1D("f5_1_"+(ifile+1)));
fitMC(dgMC_ZTheta_grid_1.getH1F("hi_delta_zvertex_Zminus_Thetahigh_"+(ifile+1)), dgMC_ZTheta_grid_1.getF1D("f6_1_"+(ifile+1)));
fitMC(dgMC_ZTheta_grid_1.getH1F("hi_delta_zvertex_Zplus_Thetalow_"+(ifile+1)), dgMC_ZTheta_grid_1.getF1D("f7_1_"+(ifile+1)));
fitMC(dgMC_ZTheta_grid_1.getH1F("hi_delta_zvertex_Zplus_Thetahigh_"+(ifile+1)), dgMC_ZTheta_grid_1.getF1D("f8_1_"+(ifile+1)));

System.out.println(" Fit ended! ");

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
