# --- !Ups
insert into statuses(world_id, character_id, reply, in_reply_to_id, text) values (1, 'hoge', false, null, 'てきすと1');
insert into statuses(world_id, character_id, reply, in_reply_to_id, text) values (1, 'hoge', false, null, 'てきすと2');
insert into statuses(world_id, character_id, reply, in_reply_to_id, text) values (1, 'hoge', false, null, 'てきすと3');
insert into statuses(world_id, character_id, reply, in_reply_to_id, text) values (1, 'hoge', false, null, 'てきすと4');
insert into statuses(world_id, character_id, reply, in_reply_to_id, text) values (1, 'hoge', false, null, 'てきすと5');

insert into statuses(world_id, character_id, reply, in_reply_to_id, text) values (2, 'hoge', false, null, 'てきすと6');
insert into statuses(world_id, character_id, reply, in_reply_to_id, text) values (2, 'hoge', false, null, 'てきすと7');
insert into statuses(world_id, character_id, reply, in_reply_to_id, text) values (2, 'hoge', false, null, 'てきすと8');
insert into statuses(world_id, character_id, reply, in_reply_to_id, text) values (2, 'hoge', false, null, 'てきすと9');
insert into statuses(world_id, character_id, reply, in_reply_to_id, text) values (2, 'hoge', false, null, 'てきすと10');

# --- !Downs
delete from statuses;