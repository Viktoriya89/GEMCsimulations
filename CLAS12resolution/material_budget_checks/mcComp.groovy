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
DataGroup dgMC = new DataGroup(3,2);
for(int ifile=0; ifile<2; ifile++) {
    H1F hi_gen_p = new H1F("hi_gen_p_"+(ifile+1), "p (GeV)", "Counts", 100, 0, 10);
    hi_gen_p.setLineColor(ifile+1);
    dgMC.addDataSet(hi_gen_p, 0);
    H1F hi_gen_theta = new H1F("hi_gen_theta_"+(ifile+1), "#theta (deg)", "Counts", 100, 0, 40);
    hi_gen_theta.setLineColor(ifile+1);
    dgMC.addDataSet(hi_gen_theta, 1);
    H1F hi_gen_phi = new H1F("hi_gen_phi_"+(ifile+1), "#phi (deg)", "Counts", 100, -180, 180);
    hi_gen_phi.setLineColor(ifile+1);
    dgMC.addDataSet(hi_gen_phi, 2);
    H1F hi_rec_p = new H1F("hi_rec_p_"+(ifile+1), "p (GeV)", "Counts", 100, 0, 10);
    hi_rec_p.setLineColor(ifile+1);
    dgMC.addDataSet(hi_rec_p, 3);
    H1F hi_rec_theta = new H1F("hi_rec_theta_"+(ifile+1), "#theta (deg)", "Counts", 100, 0, 40);
    hi_rec_theta.setLineColor(ifile+1);
    dgMC.addDataSet(hi_rec_theta, 4);
    H1F hi_rec_phi = new H1F("hi_rec_phi_"+(ifile+1), "#phi (deg)", "Counts", 100, -180, 180);
    hi_rec_phi.setLineColor(ifile+1);
    dgMC.addDataSet(hi_rec_phi, 5);
}
DataGroup dgMCpi = new DataGroup(3,2);
for(int ifile=0; ifile<2; ifile++) {
    H1F hi_gen_p = new H1F("hi_gen_p_"+(ifile+1), "p (GeV)", "Counts", 100, 0, 10);
    hi_gen_p.setLineColor(ifile+1);
    dgMCpi.addDataSet(hi_gen_p, 0);
    H1F hi_gen_theta = new H1F("hi_gen_theta_"+(ifile+1), "#theta (deg)", "Counts", 100, 0, 40);
    hi_gen_theta.setLineColor(ifile+1);
    dgMCpi.addDataSet(hi_gen_theta, 1);
    H1F hi_gen_phi = new H1F("hi_gen_phi_"+(ifile+1), "#phi (deg)", "Counts", 100, -180, 180);
    hi_gen_phi.setLineColor(ifile+1);
    dgMCpi.addDataSet(hi_gen_phi, 2);
    H1F hi_rec_p = new H1F("hi_rec_p_"+(ifile+1), "p (GeV)", "Counts", 100, 0, 10);
    hi_rec_p.setLineColor(ifile+1);
    dgMCpi.addDataSet(hi_rec_p, 3);
    H1F hi_rec_theta = new H1F("hi_rec_theta_"+(ifile+1), "#theta (deg)", "Counts", 100, 0, 40);
    hi_rec_theta.setLineColor(ifile+1);
    dgMCpi.addDataSet(hi_rec_theta, 4);
    H1F hi_rec_phi = new H1F("hi_rec_phi_"+(ifile+1), "#phi (deg)", "Counts", 100, -180, 180);
    hi_rec_phi.setLineColor(ifile+1);
    dgMCpi.addDataSet(hi_rec_phi, 5);
}
// adding new histogram page
DataGroup dgMCresol = new DataGroup(4,2);
for(int ifile=0; ifile<2; ifile++) {
    H1F hi_delta_p = new H1F("hi_delta_p_"+(ifile+1), "#Delta p / p(GeV)", "Counts", 100, -0.1, 0.1);
    hi_delta_p.setLineColor(ifile+1);
    dgMCresol.addDataSet(hi_delta_p, 0);

    H1F hi_delta_theta = new H1F("hi_delta_theta_"+(ifile+1), "#Delta #theta (deg)", "Counts", 100, -1, 1);
    hi_delta_theta.setLineColor(ifile+1);
    dgMCresol.addDataSet(hi_delta_theta, 1);

    H1F hi_delta_phi = new H1F("hi_delta_phi_"+(ifile+1), "#Delta #phi (deg)", "Counts", 100, -5.0, 5.0);
    hi_delta_phi.setLineColor(ifile+1);
    dgMCresol.addDataSet(hi_delta_phi, 2);

    H1F hi_delta_zvertex = new H1F("hi_delta_zvertex_"+(ifile+1), "#Delta Zvertex (cm)", "Counts", 100, -10.0, 10.0);
    hi_delta_zvertex.setLineColor(ifile+1);
    dgMCresol.addDataSet(hi_delta_zvertex, 3);

//test for negative particles plots
    H1F hi_p_neg = new H1F("hi_p_neg_"+(ifile+1), "P_rec neg (GeV)", "Counts", 100, 0.0, 8.0);
    hi_p_neg.setLineColor(ifile+1);
    dgMCresol.addDataSet(hi_p_neg, 4);

    H1F hi_theta_neg = new H1F("hi_theta_neg_"+(ifile+1), "#theta_rec neg (deg)", "Counts", 100, 0.0, 40.0);
    hi_theta_neg.setLineColor(ifile+1);
    dgMCresol.addDataSet(hi_theta_neg, 5);

    H1F hi_phi_neg = new H1F("hi_phi_neg_"+(ifile+1), "#phi_rec neg (deg)", "Counts", 100, -180.0, 180.0);
    hi_phi_neg.setLineColor(ifile+1);
    dgMCresol.addDataSet(hi_phi_neg, 6);

    H1F hi_zvertex_neg = new H1F("hi_zvertex_neg_"+(ifile+1), "Zvertex_rec neg (cm)", "Counts", 180, -20.0, 20.0);
    hi_zvertex_neg.setLineColor(ifile+1);
    dgMCresol.addDataSet(hi_zvertex_neg, 7);
}




for(int ifile=0; ifile<2; ifile++) {
   String  inputFile = args[ifile];
   HipoDataSource reader = new HipoDataSource();
   reader.open(inputFile);
   while(reader.hasEvent()){
	DataEvent event = reader.getNextEvent();
	if(event.hasBank("MC::Particle")) {
	   DataBank bank = event.getBank("MC::Particle");
	   if(bank.getInt("pid",0)==11) {
	      for(int loop=0; loop<bank.rows(); loop++) {
  	          Particle part = new Particle(bank.getInt("pid",loop),
	                                       bank.getFloat("px",loop), 
	                                       bank.getFloat("py",loop), 
	                                       bank.getFloat("pz",loop), 
 	                                       bank.getFloat("vx",loop), 
	                                       bank.getFloat("vy",loop), 
	                                       bank.getFloat("vz",loop));
		  if(loop==0) {			      
	             dgMC.getH1F("hi_gen_p_"+(ifile+1)).fill(part.p());
	             dgMC.getH1F("hi_gen_theta_"+(ifile+1)).fill(Math.toDegrees(part.theta()));
	             dgMC.getH1F("hi_gen_phi_"+(ifile+1)).fill(Math.toDegrees(part.phi()));
		  }
		  else if(bank.getInt("pid",loop)==-211) {
	             dgMCpi.getH1F("hi_gen_p_"+(ifile+1)).fill(part.p());
	             dgMCpi.getH1F("hi_gen_theta_"+(ifile+1)).fill(Math.toDegrees(part.theta()));
	             dgMCpi.getH1F("hi_gen_phi_"+(ifile+1)).fill(Math.toDegrees(part.phi()));
		  }
	       }	  
	   }
	}   
	if(event.hasBank("REC::Particle")) {
	      DataBank bank = event.getBank("REC::Particle");
	      for(int loop=0; loop<bank.rows(); loop++) {
	          int pid = bank.getInt("pid",loop);
		  if(pid==0) pid=211*bank.getByte("charge",loop);
		  if(pid==0) continue;
  	          Particle part = new Particle(pid,
	                                       bank.getFloat("px",loop), 
	                                       bank.getFloat("py",loop), 
	                                       bank.getFloat("pz",loop), 
 	                                       bank.getFloat("vx",loop), 
	                                       bank.getFloat("vy",loop), 
	                                       bank.getFloat("vz",loop));
		  if(loop==0 && bank.getShort("status",loop)<-2000 && bank.getInt("pid",0)==11) {			      
	             dgMC.getH1F("hi_rec_p_"+(ifile+1)).fill(part.p());
	             dgMC.getH1F("hi_rec_theta_"+(ifile+1)).fill(Math.toDegrees(part.theta()));
	             dgMC.getH1F("hi_rec_phi_"+(ifile+1)).fill(Math.toDegrees(part.phi()));
		  }
		  else if(bank.getByte("charge",loop)==-1 && ((int) bank.getShort("status",loop)/1000)==2) {
	             dgMCpi.getH1F("hi_rec_p_"+(ifile+1)).fill(part.p());
	             dgMCpi.getH1F("hi_rec_theta_"+(ifile+1)).fill(Math.toDegrees(part.theta()));
	             dgMCpi.getH1F("hi_rec_phi_"+(ifile+1)).fill(Math.toDegrees(part.phi()));
		  }
	       }	  
 	   }   
   }
   reader.close();
}

// adding the selection of negative particles to plot the resolutions
// Delta P / P = (Prec - Pgen)/Pgen
Particle partGenNeg = null;
Particle partRecNeg = null;

for(int ifile=0; ifile<2; ifile++) {
   String  inputFile = args[ifile];
   HipoDataSource reader = new HipoDataSource();
   reader.open(inputFile);
   while(reader.hasEvent()){
	DataEvent event = reader.getNextEvent();

	if(event.hasBank("TimeBasedTrkg::TBTracks")==true) {
	   DataBank bank = event.getBank("TimeBasedTrkg::TBTracks");
		int rows = bank.rows();
	      	for(int loop=0; loop<bank.rows(); loop++) {

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
			int id     = bank.getShort("id", loop);
			int sector = bank.getByte("sector", loop);

			recParticle.setProperty("chi2", bank.getFloat("chi2", loop));
			recParticle.setProperty("ndf", (float) bank.getShort("ndf", loop));

			if(recParticle.charge()<0 && recParticle.getProperty("ndf")>0) {
			
			dgMCresol.getH1F("hi_p_neg_"+(ifile+1)).fill(recParticle.p());
			dgMCresol.getH1F("hi_theta_neg_"+(ifile+1)).fill(Math.toDegrees(recParticle.theta()));
			dgMCresol.getH1F("hi_phi_neg_"+(ifile+1)).fill(Math.toDegrees(recParticle.phi()));
			dgMCresol.getH1F("hi_zvertex_neg_"+(ifile+1)).fill(recParticle.vz());
			} 
		
			if(partRecNeg==null && recParticle.charge()<0) partRecNeg=recParticle;
	       }
	}  

	if(event.hasBank("MC::Particle")==true){
            DataBank genBank = event.getBank("MC::Particle");
            int nrows = genBank.rows();
            for(int loop = 0; loop < nrows; loop++) {   
                Particle genPart = new Particle(
                                              genBank.getInt("pid", loop),
                                              genBank.getFloat("px", loop),
                                              genBank.getFloat("py", loop),
                                              genBank.getFloat("pz", loop),
                                              genBank.getFloat("vx", loop),
                                              genBank.getFloat("vy", loop),
                                              genBank.getFloat("vz", loop));
                if(genPart.charge()==-1  && partGenNeg==null && genPart.theta()!=0) partGenNeg=genPart;
                //if(genPart.charge()==1   && partGenPos==null) partGenPos=genPart;

            }
	}
	
	if(partGenNeg != null && partRecNeg != null) {

		dgMCresol.getH1F("hi_delta_p_"+(ifile+1)).fill((partRecNeg.p()-partGenNeg.p())/partGenNeg.p());
		dgMCresol.getH1F("hi_delta_theta_"+(ifile+1)).fill(Math.toDegrees(partRecNeg.theta()-partGenNeg.theta()));
		dgMCresol.getH1F("hi_delta_phi_"+(ifile+1)).fill(Math.toDegrees(partRecNeg.phi()-partGenNeg.phi()));
		dgMCresol.getH1F("hi_delta_zvertex_"+(ifile+1)).fill(partRecNeg.vz()-partGenNeg.vz());
            }
   
   }
   reader.close();
}


EmbeddedCanvasTabbed mcCanvas = new EmbeddedCanvasTabbed("MC","MCpi", "MCresol");

mcCanvas.getCanvas("MC").draw(dgMC);
mcCanvas.getCanvas("MC").setGridX(false);
mcCanvas.getCanvas("MC").setGridY(false);
mcCanvas.getCanvas("MC").setAxisFontSize(18);
mcCanvas.getCanvas("MC").setAxisTitleSize(24);
mcCanvas.getCanvas("MCpi").draw(dgMCpi);
mcCanvas.getCanvas("MCpi").setGridX(false);
mcCanvas.getCanvas("MCpi").setGridY(false);
mcCanvas.getCanvas("MCpi").setAxisFontSize(18);
mcCanvas.getCanvas("MCpi").setAxisTitleSize(24);
// adding my future resolution plots
mcCanvas.getCanvas("MCresol").draw(dgMCresol);
mcCanvas.getCanvas("MCresol").setGridX(false);
mcCanvas.getCanvas("MCresol").setGridY(false);
mcCanvas.getCanvas("MCresol").setAxisFontSize(18);
mcCanvas.getCanvas("MCresol").setAxisTitleSize(24);


JFrame frame = new JFrame("MC");
frame.setSize(1200, 1000);
frame.add(mcCanvas);
frame.setLocationRelativeTo(null);
frame.setVisible(true);
