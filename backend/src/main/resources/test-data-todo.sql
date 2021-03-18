insert into category (category_id, name)
values(1, 'test');
insert into title (title_id, title)
values(1, 'test');
insert into content (content_id, content)
values(1, 'test');
insert into todo (id, fk_category_id, fk_content_id, fk_title_id)
values(1, 1, 1, 1);