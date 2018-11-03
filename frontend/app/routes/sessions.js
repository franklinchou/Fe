import Route from '@ember/routing/route';
    
import RSVP from 'rsvp';

// routes/sessions.js
export default Route.extend({

    model(/* empty for now */) {

        // Send a call to GET /sessions
        const sessions = this.store.findAll('session');
        
        return RSVP.hash({
            sessions
        });
    }

});
