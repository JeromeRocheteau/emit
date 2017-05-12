select url, name, deleted
from analysis
where deleted = 0
limit ?,5;