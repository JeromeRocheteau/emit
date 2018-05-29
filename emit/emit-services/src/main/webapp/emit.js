var emit = {};

emit.user = {};
	    
emit.user.name = function(onSuccess, onError) {
  const req = new XMLHttpRequest();
  req.onload = function(event) {
    if (this.status === 200) {
      onSuccess(JSON.parse(this.responseText));
    } else {
      onError(this.responseText);
    }
  };
  req.onerror = function(event) {
    onError(this.responseText);
  }
  req.open('GET','/emit/user/name', true);
  req.send(null);    
};
	    
emit.user.role = function(onSuccess, onError) {
  const req = new XMLHttpRequest();
  req.onload = function(event) {
    if (this.status === 200) {
      onSuccess(JSON.parse(this.responseText));
    } else {
      onError(this.responseText);
    }
  };
  req.onerror = function(event) {
    onError(this.responseText);
  }
  req.open('GET','/emit/user/role', true);
  req.send(null);    
};
	    
emit.user.register = function(data, onSuccess, onError) {
  var parameters = "?username=" + encodeURIComponent(data.username) 
  + "&rolename=" + encodeURIComponent(data.rolename) 
  + "&password=" + encodeURIComponent(data.password) 
  + "&confirmPassword=" + encodeURIComponent(data.confirmPassword);
  const req = new XMLHttpRequest();
  req.onload = function(event) {
    if (this.status === 200) {
      onSuccess(JSON.parse(this.responseText));
    } else {
      onError(this.responseText);
    }
  };
  req.onerror = function(event) {
    onError(this.responseText);
  }
  req.open('POST','/emit/user/register' + parameters, true);
  req.send(null);    
};
	    
emit.user.update = function(data, onSuccess, onError) {
  var parameters = "?username=" + encodeURIComponent(data.username) 
  + "&rolename=" + encodeURIComponent(data.rolename) 
  + "&password=" + encodeURIComponent(data.password) 
  + "&confirmPassword=" + encodeURIComponent(data.confirmPassword);
  const req = new XMLHttpRequest();
  req.onload = function(event) {
    if (this.status === 200) {
      onSuccess(JSON.parse(this.responseText));
    } else {
      onError(this.responseText);
    }
  };
  req.onerror = function(event) {
    onError(this.responseText);
  }
  req.open('POST','/emit/user/update' + parameters, true);
  req.send(null);    
};

emit.broker= {};

emit.broker.size = function(onSuccess, onError) {
  const req = new XMLHttpRequest();
  req.onload = function(event) {
    if (this.status === 200) {
      onSuccess(JSON.parse(this.responseText));
    } else {
      onError(this.responseText);
    }
  };
  req.onerror = function(event) {
    onError(this.responseText);
  }
  req.open('GET','/emit/brokers/size', true);
  req.send(null);    
};

emit.broker.page = function(data, onSuccess, onError) {
  var parameters = "?offset=" + data.offset + "&length=" + data.length;
  const req = new XMLHttpRequest();
  req.onload = function(event) {
    if (this.status === 200) {
      onSuccess(JSON.parse(this.responseText));
    } else {
      onError(this.responseText);
    }
  };
  req.onerror = function(event) {
    onError(this.responseText);
  }
  req.open('GET','/emit/brokers/page' + parameters, true);
  req.send(null);    
};

emit.broker.list = function(onSuccess, onError) {
  const req = new XMLHttpRequest();
  req.onload = function(event) {
    if (this.status === 200) {
      onSuccess(JSON.parse(this.responseText));
    } else {
      onError(this.responseText);
    }
  };
  req.onerror = function(event) {
    onError(this.responseText);
  }
  req.open('GET','/emit/brokers/list', true);
  req.send(null);    
};
	    
emit.broker.create = function(data, onSuccess, onError) {
  var parameters = "?name=" + encodeURIComponent(data.name) 
  + "&uri=" + encodeURIComponent(data.uri) 
  + (data.username == null ? "" : "&username=" + encodeURIComponent(data.username)) 
  + (data.password == null ? "" : "&password=" + encodeURIComponent(data.password));
  const req = new XMLHttpRequest();
  req.onload = function(event) {
    if (this.status === 200) {
      onSuccess(JSON.parse(this.responseText));
    } else {
      onError(this.responseText);
    }
  };
  req.onerror = function(event) {
    onError(this.responseText);
  }
  req.open('POST','/emit/brokers/create' + parameters, true);
  req.send(null);    
};
	    
emit.broker.update = function(data, onSuccess, onError) {
  var parameters = "?name=" + encodeURIComponent(data.name) 
  + "&uri=" + encodeURIComponent(data.uri) 
  + (data.username == null ? "" : "&username=" + encodeURIComponent(data.username)) 
  + (data.password == null ? "" : "&password=" + encodeURIComponent(data.password));
  const req = new XMLHttpRequest();
  req.onload = function(event) {
    if (this.status === 200) {
      onSuccess(JSON.parse(this.responseText));
    } else {
      onError(this.responseText);
    }
  };
  req.onerror = function(event) {
    onError(this.responseText);
  }
  req.open('POST','/emit/brokers/update' + parameters, true);
  req.send(null);    
};
	    
emit.broker.delete = function(data, onSuccess, onError) {
  var parameters = "?uri=" + encodeURIComponent(data.uri);
  const req = new XMLHttpRequest();
  req.onload = function(event) {
    if (this.status === 200) {
      onSuccess(JSON.parse(this.responseText));
    } else {
      onError(this.responseText);
    }
  };
  req.onerror = function(event) {
    onError(this.responseText);
  }
  req.open('POST','/emit/brokers/delete' + parameters, true);
  req.send(null);    
};

