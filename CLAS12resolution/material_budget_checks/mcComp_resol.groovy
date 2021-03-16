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
    


    H1F hi_delta_p = new H1F("hi_delta_p_"+(ifile+1), "#Delta p / p(GeV)", "Counts", 50, -0.6, -0.2);
    hi_delta_p.setLineColor(ifile+1);
    dgMC.addDataSet(hi_delta_p, 8);

    H1F hi_delta_theta = new H1F("hi_delta_theta_"+(ifile+1), "#Delta #theta (deg)", "Counts", 50, 2.0, 3.0);
    hi_delta_theta.setLineColor(ifile+1);
    dgMC.addDataSet(hi_delta_theta, 9);

    H1F hi_delta_phi = new H1F("hi_delta_phi_"+(ifile+1), "#Delta #phi (deg)", "Counts", 50, 3.0, 4.0);
    hi_delta_phi.setLineColor(ifile+1);
    dgMC.addDataSet(hi_delta_phi, 10);

    H1F hi_delta_zvertex = new H1F("hi_delta_zvertex_"+(ifile+1), "#Delta Zvertex (cm)", "Counts", 50, 9.0, 10.0);
    hi_delta_zvertex.setLineColor(ifile+1);
    dgMC.addDataSet(hi_delta_zvertex, 11);
}

DataGroup dgMCcuts = new DataGroup(1,1);
for(int ifile=0; ifile<2; ifile++) {
    H1F hi_zvertex_neg_cutTest = new H1F("hi_zvertex_neg_cutTest_"+(ifile+1), "Z rec cut test (cm)", "Counts", 180, -20.0, 20.0);
    hi_zvertex_neg_cutTest.setLineColor(ifile+1);
    dgMCcuts.addDataSet(hi_zvertex_neg_cutTest, 0);
}

Particle partGenNeg = null;
Particle partRecNeg = null;

for(int ifile=0; ifile<2; ifile++) {
   String  inputFile = args[ifile];
   HipoDataSource reader = new HipoDataSource();
   reader.open(inputFile);

int RECrows = 0;
int MCrows = 0;


   while(reader.hasEvent()){
	DataEvent event = reader.getNextEvent();

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

					//if(recParticle.p()>2.&& Math.toDegrees(recParticle.theta())>10.) {
                        			//dgMCcuts.getH1F("hi_zvertex_neg_cutTest_"+(ifile+1)).fill(recParticle.vz());
                    				//}



		  	}
		
			if(partRecNeg==null && recParticle.charge()<0) partRecNeg=recParticle;
		
	       }
		
		if(partGenNeg != null && partRecNeg != null) {
		dgMC.getH1F("hi_delta_p_"+(ifile+1)).fill((partRecNeg.p()-partGenNeg.p())/partGenNeg.p());
		dgMC.getH1F("hi_delta_theta_"+(ifile+1)).fill(Math.toDegrees(partRecNeg.theta()-partGenNeg.theta()));
		dgMC.getH1F("hi_delta_phi_"+(ifile+1)).fill(Math.toDegrees(partRecNeg.phi()-partGenNeg.phi()));
		dgMC.getH1F("hi_delta_zvertex_"+(ifile+1)).fill(partRecNeg.vz()-partGenNeg.vz()); 
	     }

 	}


	  
   
}
   reader.close();
System.out.println("RECrows total number is " + RECrows);
System.out.println("MCrows total number is " + MCrows);

}

EmbeddedCanvasTabbed mcCanvas = new EmbeddedCanvasTabbed("MC","MCcuts");

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
