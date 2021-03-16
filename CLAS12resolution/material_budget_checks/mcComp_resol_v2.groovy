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
for(int ifile=0; ifile<4; ifile++) {
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
	//F1D f1_delta_p = new F1D("f1_delta_p","[amp]*gaus(x,[mean],[sigma])", -10.0, 10.0);
        //f1_delta_p.setParameter(0, 0);
        //f1_delta_p.setParameter(1, 0);
        //f1_delta_p.setParameter(2, 1.0);
        //f1_delta_p.setLineWidth(2);
        //f1_delta_p.setLineColor(2);
        //f1_delta_p.setOptStat("1111");
    dgMC.addDataSet(hi_delta_p, 8);
    //dgMC.addDataSet(f1_delta_p, 8);

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

DataGroup dgMCcuts = new DataGroup(4,2);
for(int ifile=0; ifile<4; ifile++) {
    H1F hi_delta_p_2Scut = new H1F("hi_delta_p_2Scut_"+(ifile+1), "#Delta P / p(GeV) 2s cut", "Counts", 100, -0.5, 0.5);
    hi_delta_p_2Scut.setLineColor(ifile+1);
    dgMCcuts.addDataSet(hi_delta_p_2Scut, 0);

    H1F hi_delta_theta_2Scut = new H1F("hi_delta_theta_2Scut_"+(ifile+1), "#Delta #theta (deg) 2s cut", "Counts", 200, -1.0, 1.0);
    hi_delta_theta_2Scut.setLineColor(ifile+1);
    dgMCcuts.addDataSet(hi_delta_theta_2Scut, 1);

    H1F hi_delta_phi_2Scut = new H1F("hi_delta_phi_2Scut_"+(ifile+1), "#Delta #phi (deg) 2s cut", "Counts", 100, -2.5, 2.5);
    hi_delta_phi_2Scut.setLineColor(ifile+1);
    dgMCcuts.addDataSet(hi_delta_phi_2Scut, 2);

    H1F hi_delta_zvertex_2Scut = new H1F("hi_delta_zvertex_2Scut_"+(ifile+1), "#Delta Zvertex (cm) 2s cut", "Counts", 200, -2.0, 2.0);
    hi_delta_zvertex_2Scut.setLineColor(ifile+1);
    dgMCcuts.addDataSet(hi_delta_zvertex_2Scut, 3);

    H1F hi_delta_p_5Scut = new H1F("hi_delta_p_5Scut_"+(ifile+1), "#Delta P / p(GeV) 5s cut", "Counts", 100, -1.5, 1.5);
    hi_delta_p_5Scut.setLineColor(ifile+1);
    dgMCcuts.addDataSet(hi_delta_p_5Scut, 4);

    H1F hi_delta_theta_5Scut = new H1F("hi_delta_theta_5Scut_"+(ifile+1), "#Delta #theta (deg) 5s cut", "Counts", 300, -2.0, 2.0);
    hi_delta_theta_5Scut.setLineColor(ifile+1);
    dgMCcuts.addDataSet(hi_delta_theta_5Scut, 5);

    H1F hi_delta_phi_5Scut = new H1F("hi_delta_phi_5Scut_"+(ifile+1), "#Delta #phi (deg) 5s cut", "Counts", 200, -3.5, 3.5);
    hi_delta_phi_5Scut.setLineColor(ifile+1);
    dgMCcuts.addDataSet(hi_delta_phi_5Scut, 6);

    H1F hi_delta_zvertex_5Scut = new H1F("hi_delta_zvertex_5Scut_"+(ifile+1), "#Delta Zvertex (cm) 5s cut", "Counts", 400, -10.0, 10.0);
    hi_delta_zvertex_5Scut.setLineColor(ifile+1);
    dgMCcuts.addDataSet(hi_delta_zvertex_5Scut, 7);
}

DataGroup dgDeltaPOnly = new DataGroup(1,1);
for(int ifile=0; ifile<4; ifile++) {
H1F hi_delta_p_only = new H1F("hi_delta_p_only_"+(ifile+1), "#Delta P (MeV)", "Counts", 500, -200.0, 200.0);
    hi_delta_p_only.setLineColor(ifile+1);
    dgDeltaPOnly.addDataSet(hi_delta_p_only, 0);
}






for(int ifile=0; ifile<4; ifile++) {
   String  inputFile = args[ifile];
   HipoDataSource reader = new HipoDataSource();
   reader.open(inputFile);

int RECrows = 0;
int MCrows = 0;

int n_entries_deltaP_2sigma = 0.0;
int n_entries_deltaTheta_2sigma = 0.0;
int n_entries_deltaPhi_2sigma = 0.0;
int n_entries_deltaZv_2sigma = 0.0;

int n_entries_deltaP_5sigma = 0.0;
int n_entries_deltaTheta_5sigma = 0.0;
int n_entries_deltaPhi_5sigma = 0.0;
int n_entries_deltaZv_5sigma = 0.0;



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

			//if(genPart.p()>5.0 && Math.toDegrees(genPart.theta())>20.0) {
				//dgMCcuts.getH1F("hi_gen_p_cut_"+(ifile+1)).fill(genPart.p());
				//dgMCcuts.getH1F("hi_zvertex_gen_cut_"+(ifile+1)).fill(genPart.vz());
			//}
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
	//System.out.println(partRecNeg.p() + " " + partGenNeg.p());
	double deltaP = (partRecNeg.p()-partGenNeg.p())/partGenNeg.p();
	double deltaP_only = (partRecNeg.p()-partGenNeg.p())*1000;
	
	double deltaTheta = (Math.toDegrees(partRecNeg.theta()-partGenNeg.theta()));
	double deltaPhi = (Math.toDegrees(partRecNeg.phi()-partGenNeg.phi()));
	double deltaZv = (partRecNeg.vz()-partGenNeg.vz());
		
		dgMC.getH1F("hi_delta_p_"+(ifile+1)).fill(deltaP);
		//dgMC.getF1D("f1_delta_p"),"same");
		//this.fitMC(dgMC.getH1F("hi_delta_p_"+(ifile+1)), dgMC.getF1D("f1_delta_p"));
		dgDeltaPOnly.getH1F("hi_delta_p_only_"+(ifile+1)).fill(deltaP_only);

		dgMC.getH1F("hi_delta_theta_"+(ifile+1)).fill(deltaTheta);
		dgMC.getH1F("hi_delta_phi_"+(ifile+1)).fill(deltaPhi);
		dgMC.getH1F("hi_delta_zvertex_"+(ifile+1)).fill(deltaZv); 

		if ( deltaP < -0.012 || deltaP > 0.008 ) {
			dgMCcuts.getH1F("hi_delta_p_2Scut_"+(ifile+1)).fill(deltaP);
			n_entries_deltaP_2sigma = n_entries_deltaP_2sigma+1;
			}
		if ( deltaP < -0.027 || deltaP > 0.023 ) {
			dgMCcuts.getH1F("hi_delta_p_5Scut_"+(ifile+1)).fill(deltaP);
			n_entries_deltaP_5sigma = n_entries_deltaP_5sigma+1;
			}

		if ( deltaTheta < -0.092 || deltaTheta > 0.120 ) {
			dgMCcuts.getH1F("hi_delta_theta_2Scut_"+(ifile+1)).fill(deltaTheta);
			n_entries_deltaTheta_2sigma = n_entries_deltaTheta_2sigma +1;
			}
		if ( deltaTheta < -0.251 || deltaTheta > 0.279 ) {
			dgMCcuts.getH1F("hi_delta_theta_5Scut_"+(ifile+1)).fill(deltaTheta);
			n_entries_deltaTheta_5sigma = n_entries_deltaTheta_5sigma +1;
			}

		if ( deltaPhi < -0.407 || deltaPhi > 0.423 ) {
			dgMCcuts.getH1F("hi_delta_phi_2Scut_"+(ifile+1)).fill(deltaPhi);
			n_entries_deltaPhi_2sigma = n_entries_deltaPhi_2sigma+1;
			}
		if ( deltaPhi < -1.028 || deltaPhi > 1.044 ) {
			dgMCcuts.getH1F("hi_delta_phi_5Scut_"+(ifile+1)).fill(deltaPhi);
			n_entries_deltaPhi_5sigma = n_entries_deltaPhi_5sigma+1;
			}

		if ( deltaZv < -0.824 || deltaZv > 0.807 ) {
			dgMCcuts.getH1F("hi_delta_zvertex_2Scut_"+(ifile+1)).fill(deltaZv);
			n_entries_deltaZv_2sigma = n_entries_deltaZv_2sigma+1;
			}
		if ( deltaZv < -2.047 || deltaZv > 2.031 ) {
			dgMCcuts.getH1F("hi_delta_zvertex_5Scut_"+(ifile+1)).fill(deltaZv);
			n_entries_deltaZv_5sigma = n_entries_deltaZv_5sigma+1;
			}
		
	}
}
   reader.close();
System.out.println("RECrows total number is " + RECrows);
System.out.println("MCrows total number is " + MCrows);
System.out.println("n_entries, 2sigma deltaP cut, is " + n_entries_deltaP_2sigma);
System.out.println("n_entries, 2sigma deltaTheta cut, is " + n_entries_deltaTheta_2sigma);
System.out.println("n_entries, 2sigma deltaPhi cut, is " + n_entries_deltaPhi_2sigma);
System.out.println("n_entries, 2sigma deltaZv cut, is " + n_entries_deltaZv_2sigma);

System.out.println("n_entries, 5sigma deltaP cut, is " + n_entries_deltaP_5sigma);
System.out.println("n_entries, 5sigma deltaTheta cut, is " + n_entries_deltaTheta_5sigma);
System.out.println("n_entries, 5sigma deltaPhi cut, is " + n_entries_deltaPhi_5sigma);
System.out.println("n_entries, 5sigma deltaZv cut, is " + n_entries_deltaZv_5sigma);
}

EmbeddedCanvasTabbed mcCanvas = new EmbeddedCanvasTabbed("MC","MCcuts","DeltaPOnly");

mcCanvas.getCanvas("MC").draw(dgMC);
mcCanvas.getCanvas("MC").setGridX(false);
mcCanvas.getCanvas("MC").setGridY(false);
mcCanvas.getCanvas("MC").setAxisFontSize(18);
mcCanvas.getCanvas("MC").setAxisTitleSize(24);

mcCanvas.getCanvas("MCcuts").draw(dgMCcuts);
mcCanvas.getCanvas("MCcuts").setGridX(false);
mcCanvas.getCanvas("MCcuts").setGridY(false);
mcCanvas.getCanvas("MCcuts").setAxisFontSize(18);
mcCanvas.getCanvas("MCcuts").setAxisTitleSize(24);

mcCanvas.getCanvas("DeltaPOnly").draw(dgDeltaPOnly);
mcCanvas.getCanvas("DeltaPOnly").setGridX(false);
mcCanvas.getCanvas("DeltaPOnly").setGridY(false);
mcCanvas.getCanvas("DeltaPOnly").setAxisFontSize(18);
mcCanvas.getCanvas("DeltaPOnly").setAxisTitleSize(24);

//mcCanvas.getCanvas("MCpi").draw(dgMCpi);
//mcCanvas.getCanvas("MCpi").setGridX(false);
//mcCanvas.getCanvas("MCpi").setGridY(false);
//mcCanvas.getCanvas("MCpi").setAxisFontSize(18);
//mcCanvas.getCanvas("MCpi").setAxisTitleSize(24);


JFrame frame = new JFrame("MC");
frame.setSize(1200, 1000);
frame.add(mcCanvas);
frame.setLocationRelativeTo(null);
frame.setVisible(true);
