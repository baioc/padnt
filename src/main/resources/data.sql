INSERT INTO notepads (directory, filename, content) VALUES
	('/', 'README', 'bla bla bla this is a test bla bla.'),
	('/', 'testme', 'asdasd \0 sadkjasdjsa \n bbbbb'),
	('/test/', 'me', 'aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa')
ON CONFLICT DO NOTHING;
