import DS from 'ember-data';
import { computed } from '@ember/object';
// import ENV from 'fe/config/environment';

export default DS.JSONAPIAdapter.extend({

    host: computed(function() {
        if (this.get('ENV.environment') === 'development') {
            return 'http://localhost:9000';
        }
    }),

    namespace: computed(function() {
        if (this.get('ENV.environment') === 'development') {
            return '';
        }
    })

});
