SELECT  meat.id
from emit.experiments exp
INNER JOIN emit.measurands med
on exp.measurand = med.process
INNER JOIN emit.environments env
on exp.environment = env.uri
INNER JOIN emit.measurementsets mest
on exp.id = mest.experiment
Inner join emit.measurements meat
on meat.measurementset = mest.id
inner join emit.instruments inst
on inst.uri = mest.instrument
inner join emit.features fe
on fe.instrument = inst.uri
where med.process = ? and fe.id =? and env.uri = ?;