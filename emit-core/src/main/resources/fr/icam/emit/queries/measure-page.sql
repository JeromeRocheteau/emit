select name, unit, deleted
from measures
where deleted = 0
limit ?,5;