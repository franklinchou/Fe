import Route from '@ember/routing/route';
import { inject } from '@ember/service';
    
import RSVP from 'rsvp';


// routes/sessions.js
export default Route.extend({

    store: inject(),

    model(/* empty for now */) {

        const store = this.get('store');

        return RSVP.hash({
            sessions: store.findAll('session')
        });
    }

});
