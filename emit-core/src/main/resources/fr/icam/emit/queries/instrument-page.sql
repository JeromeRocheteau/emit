select uri, name, deleted
from instruments
where deleted = 0
limit ?,5;