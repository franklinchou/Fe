import Controller from '@ember/controller';
import { inject } from '@ember/service';
import { reads } from '@ember/object/computed';


// controllers/session.js
export default Controller.extend({

    store: inject(),

    // Reads `sesssions` from routes/application.js
    sessions: reads('model.sessions')

});
