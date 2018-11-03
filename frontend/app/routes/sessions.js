import Route from '@ember/routing/route';
import { inject } from '@ember/service';

import RSVP from 'rsvp';

// routes/sessions.js
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
