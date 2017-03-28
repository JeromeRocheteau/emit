select uri, name, deleted
from environments
where deleted = 0
limit ?,5;