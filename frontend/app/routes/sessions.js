import Route from '@ember/routing/route';

import { inject } from '@ember/service';

export default Route.extend({

    store: inject(),

    model(/* empty for now */) {
        const store = this.get('store');

        // Send a call to GET /sessions
        return store.findAll('session');
    }

});
