import Controller from '@ember/controller';
import { computed } from '@ember/object';

import ENV from 'fe/config/environment';

export default Controller.extend({

    environment: ENV.environment,

    displayInfoHeader: computed('environment', function() {
        if (this.get('environment') === 'production') {
            return false;
        }
        return true;
    })

});
