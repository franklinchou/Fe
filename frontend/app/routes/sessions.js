import Route from '@ember/routing/route';
import { reads } from '@ember/object/computed';


// routes/sessions.js
export default Route.extend({

    sessions: reads('model.sessions')

});
