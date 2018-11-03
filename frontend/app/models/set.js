import DS from 'ember-data';

export default DS.Model.extend({

    multiplier: DS.attr('number'),

    exercise: DS.attr('string'),

    description: DS.attr('string'),

    variation: DS.attr('string'),

    weight: DS.attr('number'),

    exercises: DS.hasMany('exercise')

});
