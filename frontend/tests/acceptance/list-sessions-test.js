import { module, test } from 'qunit';
import { visit, currentURL } from '@ember/test-helpers';
import { setupApplicationTest } from 'ember-qunit';

module('Acceptance | list sessions', function(hooks) {
  setupApplicationTest(hooks);

  test('visiting /list-sessions', async function(assert) {
    await visit('/list-sessions');

    assert.equal(currentURL(), '/list-sessions');
  });
});
