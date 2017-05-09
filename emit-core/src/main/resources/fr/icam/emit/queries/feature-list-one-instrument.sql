select id, measure, instrument, no_order, factor, name
from features
where features.instrument = ?;