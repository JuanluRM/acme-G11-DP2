
package acme.features.authenticated.assistant;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.framework.components.accounts.Authenticated;
import acme.framework.services.AbstractService;
import acme.roles.Assistant;

@Service
public class AuthenticatedAssistantUpdateService extends AbstractService<Authenticated, Assistant> {

	// Internal state ---------------------------------------------------------

	@Autowired
	protected AutheticatedAssistantRepository repository;

	// AbstractService interface ----------------------------------------------


	@Override
	public void check() {
		//Comprobar si existen los datos de entrada?

		//boolean status;
		//status = super.getRequest().hasData("id", int.class);
		super.getResponse().setChecked(true);//status

	}
}
