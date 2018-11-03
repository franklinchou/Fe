import DS from 'ember-data';
import { computed } from '@ember/object';

import ENV from 'fe/config/environment';

export default DS.JSONAPIAdapter.extend({

    environment: ENV.environment,

    host: computed('environment', function() {
        if (this.get('environment') === 'development') {
            return 'http://localhost:9000';
        }
    }),

    namespace: computed('environment', function() {
        if (this.get('environment') === 'development') {
            return '';
        }
    })

});
