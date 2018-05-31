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

emit.brokers= {};

emit.brokers.size = function(onSuccess, onError) {
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

emit.brokers.page = function(data, onSuccess, onError) {
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

emit.brokers.list = function(onSuccess, onError) {
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
	    
emit.brokers.create = function(data, onSuccess, onError) {
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
	    
emit.brokers.update = function(data, onSuccess, onError) {
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
	    
emit.brokers.delete = function(data, onSuccess, onError) {
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

emit.clients = {};

emit.clients.size = function(onSuccess, onError) {
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
  req.open('GET','/emit/clients/size', true);
  req.send(null);    
};

emit.clients.page = function(data, onSuccess, onError) {
  var parameters = "?offset=" + data.offset 
  + "&length=" + data.length;
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
  req.open('GET','/emit/clients/page' + parameters, true);
  req.send(null);    
};
	    
emit.clients.create = function(data, onSuccess, onError) {
  var parameters = "?name=" + encodeURIComponent(data.name) 
  + "&broker=" + encodeURIComponent(data.broker) 
  + "&open=" + data.open;;
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
  req.open('POST','/emit/clients/create' + parameters, true);
  req.send(null);    
};
	    
emit.clients.update = function(data, onSuccess, onError) {
  var parameters = "?uuid=" + data.uuid 
  + "&name=" + encodeURIComponent(data.name) 
  + "&broker=" + encodeURIComponent(data.broker) 
  + "&open=" + data.open;
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
  req.open('POST','/emit/clients/update' + parameters, true);
  req.send(null);    
};
	    
emit.clients.delete = function(data, onSuccess, onError) {
  var parameters = "?uuid=" + data.uuid;
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
  req.open('POST','/emit/clients/delete' + parameters, true);
  req.send(null);    
};

emit.clients.connected = function(data, onSuccess, onError) {
  var parameters = "?client=" + data.uuid;
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
  req.open('GET','/emit/clients/connected' + parameters, true);
  req.send(null);    
};
	    
emit.clients.connect = function(data, onSuccess, onError) {
  var parameters = "?client=" + data.uuid;
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
  req.open('POST','/emit/clients/connect' + parameters, true);
  req.send(null);    
};
	    
emit.clients.disconnect = function(data, onSuccess, onError) {
  var parameters = "?client=" + data.uuid 
  + "&connect=" + data.connected.id;
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
  req.open('POST','/emit/clients/disconnect' + parameters, true);
  req.send(null);    
};

emit.clients.subscribing = function(data, onSuccess, onError) {
  var parameters = "?client=" + data.uuid;
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
  req.open('GET','/emit/clients/subscribing' + parameters, true);
  req.send(null);    
};
	    
emit.clients.subscribe = function(data, onSuccess, onError) {
  var parameters = "?client=" + data.uuid 
  + "&topic=" + encodeURIComponent(data.filter);
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
  req.open('POST','/emit/clients/subscribe' + parameters, true);
  req.send(null);    
};
	    
emit.clients.unsubscribe = function(data, onSuccess, onError) {
  var parameters = "?client=" + data.uuid 
  + "&topic=" + encodeURIComponent(data.subscribed.topic) 
  + "&subscribe=" + data.subscribed.id;
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
  req.open('POST','/emit/clients/unsubscribe' + parameters, true);
  req.send(null);    
};
	    
emit.clients.publish = function(data, onSuccess, onError) {
  var parameters = "?client=" + data.uuid 
  + "&topic=" + encodeURIComponent(data.topic) 
  + "&qos=" + data.qos 
  + "&retained=" + data.retained 
  + "&persisted=" + data.persisted 
  + "&payload=" + encodeURIComponent(data.payload);
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
  req.open('POST','/emit/clients/publish' + parameters, true);
  req.send(null);    
};

