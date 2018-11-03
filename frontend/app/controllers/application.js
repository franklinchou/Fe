import Controller from '@ember/controller';
import ENV from 'fe/config/environment';
import { computed } from '@ember/object';

export default Controller.extend({

    environment: ENV.environment,

    displayInfoHeader: computed('environment', function() {
        if (this.get('environment') === 'production') {
            return false;
        }
        return true;
    })

});
