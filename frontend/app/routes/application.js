import Route from '@ember/routing/route';
import { inject } from '@ember/service';

import RSVP from 'rsvp';
import session from '../controllers/session';

// routes/application.js
export default Route.extend({

    store: inject(),

    model(/* empty for now */) {
        const store = this.get('store');

        // Send a call to GET /sessions
        const sessions = store.findAll('session');

        console.log(sessions);
        
        return RSVP.hash({
            sessions
        });
    }

});
