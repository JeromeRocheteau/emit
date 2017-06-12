select id, analysis, measure, measurement, 'value'
from results
where results.measurement = ?;