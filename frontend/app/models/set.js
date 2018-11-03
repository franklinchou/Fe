import DS from 'ember-data';

export default DS.Model.extend({

    multiplier: DS.attr('number'),

    exercise: DS.attr('exercise'),

    description: DS.attr('description'),

    variation: DS.attr('variation'),

    weight: DS.attr('weight'),

    exercises: DS.hasMany('exercise')

});
