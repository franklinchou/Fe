import Controller from '@ember/controller';
import { reads } from '@ember/object/computed';

// controllers/session.js
export default Controller.extend({

    // Reads `sesssions` from routes/application.js
    sessions: reads('model.sessions')

});
