import Controller from '@ember/controller';

import { inject } from '@ember/service';
import { readOnly } from '@ember/object/computed';

// controllers/session.js
export default Controller.extend({

    store: inject(),

    sessions: readOnly('model')

});
