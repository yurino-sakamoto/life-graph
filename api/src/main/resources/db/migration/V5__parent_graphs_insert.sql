INSERT INTO `parent_graphs` (
  `id`,
  `user_id`,
  `created_at`,
  `updated_at`
) VALUES
  (null, 3, cast( now() as date), cast( now() as datetime )),
  (null, 1, cast( now() as date), cast( now() as datetime )),
  (null, 2, cast( now() as date), cast( now() as datetime ));