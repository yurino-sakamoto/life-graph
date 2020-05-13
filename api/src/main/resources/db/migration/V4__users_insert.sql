INSERT INTO `users` (
  `id`,
  `address`,
  `pass`,
  `authority`,
  `created_at`,
  `updated_at`,
  `name`
) VALUES
  (null,'yu.okazaki@seattleconsulting.co.jp', '666', 1, cast( now() as date), cast( now() as datetime ), '悠'),
  (null,'yurino.sakamoto@seattleconsulting.co.jp', '777', 3, cast( now() as date), cast( now() as datetime ), 'ゆりの'),
  (null,'keita.yamaoka@seattleconsulting.co.jp', '222', 2, cast( now() as date), cast( now() as datetime ), 'けいた');