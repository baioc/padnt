INSERT INTO notepads (directory, filename, content)
	VALUES ('/', 'my', '<- look over here for more stuff')
	, ('/my/', 'notes', 'Taking notes is good ...')
	, ('/my/', 'stuff', '...
Having them online more so
This a dumb haiku')
	, ('/my/', 'online-notepad', 'Byen''t, World!')
ON CONFLICT DO NOTHING;
