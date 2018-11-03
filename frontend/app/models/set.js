import DS from 'ember-data';

export default DS.Model.extend({

    session: DS.belongsTo('session'),

    multiplier: DS.attr('number'),

    exercise: DS.attr('string'),

    description: DS.attr('string'),

    variation: DS.attr('string'),

    weight: DS.attr('number'),

    exercises: DS.hasMany('exercise')

});
