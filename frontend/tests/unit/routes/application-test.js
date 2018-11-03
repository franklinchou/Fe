import { module, test } from 'qunit';
import { setupTest } from 'ember-qunit';

module('Unit | Route | application.js', function(hooks) {
  setupTest(hooks);

  test('it exists', function(assert) {
    let route = this.owner.lookup('route:application.js');
    assert.ok(route);
  });
});
