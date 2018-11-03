import Route from '@ember/routing/route';
// import ENV from 'iris-web/config/environment';

export default Route.extend({

    // appName: ENV.name,

    // environment: ENV.environment,

    model() {
        return this.store.findAll('session');
    }

});
