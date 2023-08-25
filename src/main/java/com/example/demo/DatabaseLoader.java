package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class DatabaseLoader implements CommandLineRunner {

	private final InstrumentoRepository repositoryI;
	private final MusicoRepository repositoryM;
	private final BandaRepository repositoryB;
	private final IntegranteRepository repositoryN;

	@Autowired
	public DatabaseLoader(
		InstrumentoRepository repositoryI,
		MusicoRepository repositoryM,
		BandaRepository repositoryB,
		IntegranteRepository repositoryN
		) {
		this.repositoryI = repositoryI;
		this.repositoryM = repositoryM;
		this.repositoryB = repositoryB;
		this.repositoryN = repositoryN;
	}

	@Override
	public void run(String... strings) throws Exception {

		this.repositoryI.save(new Instrumento("Rellenita", "Wilter", ""));
		this.repositoryI.save(new Instrumento("Cocoa","Wilter",""));
		this.repositoryI.save(new Instrumento("Pan","Wilter",""));
		Instrumento iVoz = new Instrumento("Chocolate","Wilter",".");
		Instrumento wVoz = new Instrumento("Maltiada","Wilter",".");
		this.repositoryI.save(iVoz);
		Instrumento iGuitarraElectrica = new Instrumento("Rellenita","Wilter", ".");
		this.repositoryI.save(iGuitarraElectrica);
		this.repositoryI.save(new Instrumento("Cocoa","Wilter","."));

		this.repositoryI.save(wVoz);
		Instrumento iGuitarraElectri = new Instrumento("Galletas","Wilter", ".");
		this.repositoryI.save(iGuitarraElectri);
		this.repositoryI.save(new Instrumento("Cocoa","Wilter","."));

		this.repositoryM.save(new Musico("Jose"));
		Musico mFreddy = new Musico("Jorge");
		this.repositoryM.save(mFreddy);
		Musico mBrian = new Musico("Marcos");
		this.repositoryM.save(mBrian);

		Banda bQueen = new Banda("P5054");
		this.repositoryB.save(bQueen);

		Banda bQuee = new Banda("P4373");
		this.repositoryB.save(bQueen);

		this.repositoryN.save(new Integrante(bQueen, mFreddy, iVoz));
		this.repositoryN.save(new Integrante(bQueen, mBrian, iGuitarraElectrica));
        
		

	}
}