package DependencyInjection;

import org.picocontainer.DefaultPicoContainer;
import org.picocontainer.MutablePicoContainer;
import org.picocontainer.injectors.SetterInjection;

public class Container {
	public MutablePicoContainer mPico;

	public Container() {
		mPico = configureContainer();
	}

	private MutablePicoContainer configureContainer() {
		MutablePicoContainer pico = new DefaultPicoContainer(new SetterInjection());
		pico.addComponent(Escola.class);
		pico.addComponent(Aluno.class);
		return pico;
	}

}
