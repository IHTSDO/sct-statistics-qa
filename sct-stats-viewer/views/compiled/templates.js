this["JST"] = this["JST"] || {};

this["JST"]["views/analyticsResultsIndex.hbs"] = Handlebars.template(function (Handlebars,depth0,helpers,partials,data) {
  this.compilerInfo = [4,'>= 1.0.0'];
helpers = this.merge(helpers, Handlebars.helpers); data = data || {};
  var buffer = "", stack1, functionType="function", escapeExpression=this.escapeExpression, self=this;

function program1(depth0,data) {
  
  var buffer = "", stack1, helper;
  buffer += "\n                                    <tr class=\"small\">\n                                        <td class=\"\">";
  if (helper = helpers.patternId) { stack1 = helper.call(depth0, {hash:{},data:data}); }
  else { helper = (depth0 && depth0.patternId); stack1 = typeof helper === functionType ? helper.call(depth0, {hash:{},data:data}) : helper; }
  buffer += escapeExpression(stack1)
    + "</td>\n                                        <td class=\"\">";
  if (helper = helpers.name) { stack1 = helper.call(depth0, {hash:{},data:data}); }
  else { helper = (depth0 && depth0.name); stack1 = typeof helper === functionType ? helper.call(depth0, {hash:{},data:data}) : helper; }
  buffer += escapeExpression(stack1)
    + "</td>\n                                        <!--<td class=\"text-right\">";
  if (helper = helpers.severity) { stack1 = helper.call(depth0, {hash:{},data:data}); }
  else { helper = (depth0 && depth0.severity); stack1 = typeof helper === functionType ? helper.call(depth0, {hash:{},data:data}) : helper; }
  buffer += escapeExpression(stack1)
    + "</td>-->\n                                        <td class=\"text-right\">";
  if (helper = helpers.resultCount) { stack1 = helper.call(depth0, {hash:{},data:data}); }
  else { helper = (depth0 && depth0.resultCount); stack1 = typeof helper === functionType ? helper.call(depth0, {hash:{},data:data}) : helper; }
  buffer += escapeExpression(stack1)
    + "</td>\n                                        <td class=\"\"><a class=\"btn btn-material-light-blue btn-sm load-sample-button\" style=\"margin: 0px;\" href=\"javascript:showSample(";
  if (helper = helpers.patternId) { stack1 = helper.call(depth0, {hash:{},data:data}); }
  else { helper = (depth0 && depth0.patternId); stack1 = typeof helper === functionType ? helper.call(depth0, {hash:{},data:data}) : helper; }
  buffer += escapeExpression(stack1)
    + ");\" role=\"button\">Results</a></td>\n                                    </tr>\n                                    <tr class=\"small\" style=\"display: none;\" id=\"sample-row-";
  if (helper = helpers.patternId) { stack1 = helper.call(depth0, {hash:{},data:data}); }
  else { helper = (depth0 && depth0.patternId); stack1 = typeof helper === functionType ? helper.call(depth0, {hash:{},data:data}) : helper; }
  buffer += escapeExpression(stack1)
    + "\">\n                                        <td class=\"\"></td>\n                                        <td colspan=3 class=\"\">\n                                        	<p>";
  if (helper = helpers.description) { stack1 = helper.call(depth0, {hash:{},data:data}); }
  else { helper = (depth0 && depth0.description); stack1 = typeof helper === functionType ? helper.call(depth0, {hash:{},data:data}) : helper; }
  buffer += escapeExpression(stack1)
    + "</p>\n                                            <table class=\"table table-striped\">\n                                                ";
  stack1 = helpers.each.call(depth0, (depth0 && depth0.resultSample), {hash:{},inverse:self.noop,fn:self.program(2, program2, data),data:data});
  if(stack1 || stack1 === 0) { buffer += stack1; }
  buffer += "\n                                            </table>\n                                            <!--<div class=\"bs-component\">-->\n                                                <!--<div class=\"list-group\">-->\n                                                    <!--";
  stack1 = helpers.each.call(depth0, (depth0 && depth0.resultSample), {hash:{},inverse:self.noop,fn:self.program(4, program4, data),data:data});
  if(stack1 || stack1 === 0) { buffer += stack1; }
  buffer += "-->\n                                                <!--</div>-->\n                                            <!--</div>-->\n                                            <p class=\"text-center\"><a href=\"javascript:loadFullResult('";
  if (helper = helpers.resultFileName) { stack1 = helper.call(depth0, {hash:{},data:data}); }
  else { helper = (depth0 && depth0.resultFileName); stack1 = typeof helper === functionType ? helper.call(depth0, {hash:{},data:data}) : helper; }
  buffer += escapeExpression(stack1)
    + "','";
  if (helper = helpers.name) { stack1 = helper.call(depth0, {hash:{},data:data}); }
  else { helper = (depth0 && depth0.name); stack1 = typeof helper === functionType ? helper.call(depth0, {hash:{},data:data}) : helper; }
  buffer += escapeExpression(stack1)
    + "','";
  if (helper = helpers.description) { stack1 = helper.call(depth0, {hash:{},data:data}); }
  else { helper = (depth0 && depth0.description); stack1 = typeof helper === functionType ? helper.call(depth0, {hash:{},data:data}) : helper; }
  buffer += escapeExpression(stack1)
    + "','term','','','');\">Load all ";
  if (helper = helpers.resultCount) { stack1 = helper.call(depth0, {hash:{},data:data}); }
  else { helper = (depth0 && depth0.resultCount); stack1 = typeof helper === functionType ? helper.call(depth0, {hash:{},data:data}) : helper; }
  buffer += escapeExpression(stack1)
    + " results</a></p>\n                                        </td>\n                                        <td class=\"\"></td>\n                                    </tr>\n                                ";
  return buffer;
  }
function program2(depth0,data) {
  
  var buffer = "", stack1, helper;
  buffer += "\n                                                    <tr>\n                                                        <td class=\"\">";
  if (helper = helpers.conceptId) { stack1 = helper.call(depth0, {hash:{},data:data}); }
  else { helper = (depth0 && depth0.conceptId); stack1 = typeof helper === functionType ? helper.call(depth0, {hash:{},data:data}) : helper; }
  buffer += escapeExpression(stack1)
    + "</td>\n                                                        <td class=\"\">";
  if (helper = helpers.term) { stack1 = helper.call(depth0, {hash:{},data:data}); }
  else { helper = (depth0 && depth0.term); stack1 = typeof helper === functionType ? helper.call(depth0, {hash:{},data:data}) : helper; }
  buffer += escapeExpression(stack1)
    + "</td>\n                                                        <td class=\"small\">";
  if (helper = helpers.matchDescription) { stack1 = helper.call(depth0, {hash:{},data:data}); }
  else { helper = (depth0 && depth0.matchDescription); stack1 = typeof helper === functionType ? helper.call(depth0, {hash:{},data:data}) : helper; }
  buffer += escapeExpression(stack1)
    + "</td>\n                                                    </tr>\n                                                ";
  return buffer;
  }

function program4(depth0,data) {
  
  var buffer = "", stack1, helper;
  buffer += "-->\n                                                        <!--<div class=\"list-group-item\">-->\n                                                            <!--<div class=\"row-action-primary\">-->\n                                                                <!--<i class=\"material-icons\"></i>-->\n                                                            <!--</div>-->\n                                                            <!--<div class=\"row-content\">-->\n                                                                <!--<div class=\"least-content\">CID: ";
  if (helper = helpers.conceptId) { stack1 = helper.call(depth0, {hash:{},data:data}); }
  else { helper = (depth0 && depth0.conceptId); stack1 = typeof helper === functionType ? helper.call(depth0, {hash:{},data:data}) : helper; }
  buffer += escapeExpression(stack1)
    + "</div>-->\n                                                                <!--<h4 class=\"list-group-item-heading\">";
  if (helper = helpers.term) { stack1 = helper.call(depth0, {hash:{},data:data}); }
  else { helper = (depth0 && depth0.term); stack1 = typeof helper === functionType ? helper.call(depth0, {hash:{},data:data}) : helper; }
  buffer += escapeExpression(stack1)
    + "Placeholder term</h4>-->\n\n                                                                <!--<p class=\"list-group-item-text\">";
  if (helper = helpers.matchDescription) { stack1 = helper.call(depth0, {hash:{},data:data}); }
  else { helper = (depth0 && depth0.matchDescription); stack1 = typeof helper === functionType ? helper.call(depth0, {hash:{},data:data}) : helper; }
  buffer += escapeExpression(stack1)
    + "</p>-->\n                                                            <!--</div>-->\n                                                        <!--</div>-->\n                                                        <!--<div class=\"list-group-separator\"></div>-->\n                                                    <!--";
  return buffer;
  }

  buffer += "<div class=\"col-md-12\">\n    <div class=\"card\" style=\"overflow: auto;\">\n        <div class=\"card-height-indicator\"></div>\n        <div class=\"card-content\">\n            <div class=\"card-body\">\n                <h2>Patterns</h2>\n                <div class=\"row\">\n                    <div class=\"col-lg-12\">\n                        <div class=\"bs-component\">\n                            <table class=\"table table-striped\">\n                                <thead>\n                                <tr>\n                                    <th class=\"col-md-1\">Id</th>\n                                    <th class=\"col-md-7\">Name</th>\n                                    <!--<th class=\"col-md-1 text-right\">Severity</th>-->\n                                    <th class=\"col-md-1 text-right\">Count</th>\n                                    <th class=\"col-md-3\"></th>\n                                </tr>\n                                </thead>\n                                <tbody>\n                                ";
  stack1 = helpers.each.call(depth0, ((stack1 = ((stack1 = (depth0 && depth0.report)),stack1 == null || stack1 === false ? stack1 : stack1.patternProcess)),stack1 == null || stack1 === false ? stack1 : stack1.patterns), {hash:{},inverse:self.noop,fn:self.program(1, program1, data),data:data});
  if(stack1 || stack1 === 0) { buffer += stack1; }
  buffer += "\n                                </tbody>\n                                <tfoot>\n                                    <tr>\n                                        <!--<td colspan=\"6\">(*) Percentage of existing concepts in hierarchy which are Sufficiently Defined (SD). For the root concept the value includes all SNOMED CT.<br>-->\n                                        </td>\n                                    </tr>\n                                </tfoot>\n                            </table>\n                        </div><!-- /example -->\n                    </div>\n                </div>\n            </div>\n            <footer class=\"card-footer\">\n                <!--<button class=\"btn btn-flat\">Share</button>-->\n                <!--<button class=\"btn btn-flat btn-warning\">Learn More</button>-->\n            </footer>\n        </div>\n    </div>\n</div>\n";
  return buffer;
  });

this["JST"]["views/existingConceptsCount.hbs"] = Handlebars.template(function (Handlebars,depth0,helpers,partials,data) {
  this.compilerInfo = [4,'>= 1.0.0'];
helpers = this.merge(helpers, Handlebars.helpers); data = data || {};
  var buffer = "", stack1, functionType="function", escapeExpression=this.escapeExpression, self=this;

function program1(depth0,data) {
  
  var buffer = "", stack1, helper;
  buffer += "\n                                    <tr class=\"small\">\n                                        <td>";
  if (helper = helpers.term) { stack1 = helper.call(depth0, {hash:{},data:data}); }
  else { helper = (depth0 && depth0.term); stack1 = typeof helper === functionType ? helper.call(depth0, {hash:{},data:data}) : helper; }
  buffer += escapeExpression(stack1)
    + "</td>\n                                        <td class=\"text-right\">";
  if (helper = helpers.AnyChange) { stack1 = helper.call(depth0, {hash:{},data:data}); }
  else { helper = (depth0 && depth0.AnyChange); stack1 = typeof helper === functionType ? helper.call(depth0, {hash:{},data:data}) : helper; }
  buffer += escapeExpression(stack1)
    + "</td>\n                                        <td class=\"text-right\">";
  if (helper = helpers.StatedDefChange) { stack1 = helper.call(depth0, {hash:{},data:data}); }
  else { helper = (depth0 && depth0.StatedDefChange); stack1 = typeof helper === functionType ? helper.call(depth0, {hash:{},data:data}) : helper; }
  buffer += escapeExpression(stack1)
    + "</td>\n                                        <td class=\"text-right\">";
  if (helper = helpers.InferredDefChange) { stack1 = helper.call(depth0, {hash:{},data:data}); }
  else { helper = (depth0 && depth0.InferredDefChange); stack1 = typeof helper === functionType ? helper.call(depth0, {hash:{},data:data}) : helper; }
  buffer += escapeExpression(stack1)
    + "</td>\n                                        <td class=\"text-right\">";
  if (helper = helpers.Primitive2SD) { stack1 = helper.call(depth0, {hash:{},data:data}); }
  else { helper = (depth0 && depth0.Primitive2SD); stack1 = typeof helper === functionType ? helper.call(depth0, {hash:{},data:data}) : helper; }
  buffer += escapeExpression(stack1)
    + "</td>\n                                        <td class=\"text-right\">";
  if (helper = helpers.SD2Primitive) { stack1 = helper.call(depth0, {hash:{},data:data}); }
  else { helper = (depth0 && depth0.SD2Primitive); stack1 = typeof helper === functionType ? helper.call(depth0, {hash:{},data:data}) : helper; }
  buffer += escapeExpression(stack1)
    + "</td>\n                                        <td class=\"text-right\">";
  if (helper = helpers.DescriptionChange) { stack1 = helper.call(depth0, {hash:{},data:data}); }
  else { helper = (depth0 && depth0.DescriptionChange); stack1 = typeof helper === functionType ? helper.call(depth0, {hash:{},data:data}) : helper; }
  buffer += escapeExpression(stack1)
    + "</td>\n                                        <td class=\"text-right\">";
  if (helper = helpers.FSNChange) { stack1 = helper.call(depth0, {hash:{},data:data}); }
  else { helper = (depth0 && depth0.FSNChange); stack1 = typeof helper === functionType ? helper.call(depth0, {hash:{},data:data}) : helper; }
  buffer += escapeExpression(stack1)
    + "</td>\n                                    </tr>\n                                ";
  return buffer;
  }

  buffer += "<div class=\"col-md-12\">\n    <div class=\"card\">\n        <div class=\"card-height-indicator\"></div>\n        <div class=\"card-content\">\n            <div class=\"card-body\">\n                <h2>Changes to Existing Concepts - Counts by Change Type (*)</h2>\n                <div class=\"row\">\n                    <div class=\"col-lg-12\">\n                        <div class=\"bs-component\">\n                            <table class=\"table table-striped table-hover \">\n                                <thead>\n                                <tr>\n                                    <th>Hierarchy</th>\n                                    <th class=\"text-right\">Any</th>\n                                    <th class=\"text-right\">Stated Defn</th>\n                                    <th class=\"text-right\">Inferred Defn</th>\n                                    <th class=\"text-right\">P to SD </th>\n                                    <th class=\"text-right\">SD to P</th>\n                                    <th class=\"text-right\">Description Change</th>\n                                    <th class=\"text-right\">FSN Change</th>\n                                </tr>\n                                </thead>\n                                <tbody>\n                                ";
  stack1 = helpers.each.call(depth0, (depth0 && depth0.rows), {hash:{},inverse:self.noop,fn:self.program(1, program1, data),data:data});
  if(stack1 || stack1 === 0) { buffer += stack1; }
  buffer += "\n                                </tbody>\n                                <tfoot>\n                                    <tr class=\"small\"><td colspan=\"8\">(*) Stated Defn = Change to stated concept definition<br>\n                                        Inferred Defn = Change to inferred concept definition<br>\n                                        P to SD = Change in concept definition status from Primitive to Sufficiently Defined<br>\n                                        SD to P = Change in concept definition status Sufficiently Defined to Primitive</td></tr>\n                                </tfoot>\n                            </table>\n                        </div><!-- /example -->\n                    </div>\n                </div>\n            </div>\n            <footer class=\"card-footer\">\n                <!--<button class=\"btn btn-flat\">Share</button>-->\n                <!--<button class=\"btn btn-flat btn-warning\">Learn More</button>-->\n            </footer>\n        </div>\n    </div>\n</div>\n";
  return buffer;
  });

this["JST"]["views/existingConceptsPercent.hbs"] = Handlebars.template(function (Handlebars,depth0,helpers,partials,data) {
  this.compilerInfo = [4,'>= 1.0.0'];
helpers = this.merge(helpers, Handlebars.helpers); data = data || {};
  var buffer = "", stack1, functionType="function", escapeExpression=this.escapeExpression, self=this;

function program1(depth0,data) {
  
  var buffer = "", stack1, helper;
  buffer += "\n                                    <tr class=\"small\">\n                                        <td>";
  if (helper = helpers.term) { stack1 = helper.call(depth0, {hash:{},data:data}); }
  else { helper = (depth0 && depth0.term); stack1 = typeof helper === functionType ? helper.call(depth0, {hash:{},data:data}) : helper; }
  buffer += escapeExpression(stack1)
    + "</td>\n                                        <td class=\"text-right\">";
  if (helper = helpers.PercOfAnyChange) { stack1 = helper.call(depth0, {hash:{},data:data}); }
  else { helper = (depth0 && depth0.PercOfAnyChange); stack1 = typeof helper === functionType ? helper.call(depth0, {hash:{},data:data}) : helper; }
  buffer += escapeExpression(stack1)
    + "%</td>\n                                        <td class=\"text-right\">";
  if (helper = helpers.PercOfStatedDefChange) { stack1 = helper.call(depth0, {hash:{},data:data}); }
  else { helper = (depth0 && depth0.PercOfStatedDefChange); stack1 = typeof helper === functionType ? helper.call(depth0, {hash:{},data:data}) : helper; }
  buffer += escapeExpression(stack1)
    + "%</td>\n                                        <td class=\"text-right\">";
  if (helper = helpers.PercOfInferredDefChange) { stack1 = helper.call(depth0, {hash:{},data:data}); }
  else { helper = (depth0 && depth0.PercOfInferredDefChange); stack1 = typeof helper === functionType ? helper.call(depth0, {hash:{},data:data}) : helper; }
  buffer += escapeExpression(stack1)
    + "%</td>\n                                        <td class=\"text-right\">";
  if (helper = helpers.PercOfPrimitive2SD) { stack1 = helper.call(depth0, {hash:{},data:data}); }
  else { helper = (depth0 && depth0.PercOfPrimitive2SD); stack1 = typeof helper === functionType ? helper.call(depth0, {hash:{},data:data}) : helper; }
  buffer += escapeExpression(stack1)
    + "%</td>\n                                        <td class=\"text-right\">";
  if (helper = helpers.PercOfSD2Primitive) { stack1 = helper.call(depth0, {hash:{},data:data}); }
  else { helper = (depth0 && depth0.PercOfSD2Primitive); stack1 = typeof helper === functionType ? helper.call(depth0, {hash:{},data:data}) : helper; }
  buffer += escapeExpression(stack1)
    + "%</td>\n                                        <td class=\"text-right\">";
  if (helper = helpers.PercOfDescriptionChange) { stack1 = helper.call(depth0, {hash:{},data:data}); }
  else { helper = (depth0 && depth0.PercOfDescriptionChange); stack1 = typeof helper === functionType ? helper.call(depth0, {hash:{},data:data}) : helper; }
  buffer += escapeExpression(stack1)
    + "%</td>\n                                        <td class=\"text-right\">";
  if (helper = helpers.PercOfFSNChange) { stack1 = helper.call(depth0, {hash:{},data:data}); }
  else { helper = (depth0 && depth0.PercOfFSNChange); stack1 = typeof helper === functionType ? helper.call(depth0, {hash:{},data:data}) : helper; }
  buffer += escapeExpression(stack1)
    + "%</td>\n                                    </tr>\n                                ";
  return buffer;
  }

  buffer += "<div class=\"col-md-12\">\n    <div class=\"card\">\n        <div class=\"card-height-indicator\"></div>\n        <div class=\"card-content\">\n            <div class=\"card-body\">\n                <h2>Changes to Existing Concepts - Percentage of Concepts with each Change Type (*)</h2>\n                <div class=\"row\">\n                    <div class=\"col-lg-12\">\n                        <div class=\"bs-component\">\n                            <table class=\"table table-striped table-hover \">\n                                <thead>\n                                <tr>\n                                    <th>Hierarchy</th>\n                                    <th class=\"text-right\">Any</th>\n                                    <th class=\"text-right\">Stated Defn</th>\n                                    <th class=\"text-right\">Inferred Defn</th>\n                                    <th class=\"text-right\">P to SD </th>\n                                    <th class=\"text-right\">SD to P</th>\n                                    <th class=\"text-right\">Description Change</th>\n                                    <th class=\"text-right\">FSN Change</th>\n                                </tr>\n                                </thead>\n                                <tbody>\n                                ";
  stack1 = helpers.each.call(depth0, (depth0 && depth0.rows), {hash:{},inverse:self.noop,fn:self.program(1, program1, data),data:data});
  if(stack1 || stack1 === 0) { buffer += stack1; }
  buffer += "\n                                </tbody>\n                                <tfoot>\n                                <tr class=\"small\"><td colspan=\"8\">(*) Stated Defn = Change to stated concept definition<br>\n                                    Inferred Defn = Change to inferred concept definition<br>\n                                    P to SD = Change in concept definition status from Primitive to Sufficiently Defined<br>\n                                    SD to P = Change in concept definition status Sufficiently Defined to Primitive</td></tr>\n                                </tfoot>\n                            </table>\n                        </div><!-- /example -->\n                    </div>\n                </div>\n            </div>\n            <footer class=\"card-footer\">\n                <!--<button class=\"btn btn-flat\">Share</button>-->\n                <!--<button class=\"btn btn-flat btn-warning\">Learn More</button>-->\n            </footer>\n        </div>\n    </div>\n</div>\n";
  return buffer;
  });

this["JST"]["views/generalReleaseStatistics.hbs"] = Handlebars.template(function (Handlebars,depth0,helpers,partials,data) {
  this.compilerInfo = [4,'>= 1.0.0'];
helpers = this.merge(helpers, Handlebars.helpers); data = data || {};
  var buffer = "", stack1, functionType="function", escapeExpression=this.escapeExpression, self=this;

function program1(depth0,data) {
  
  var buffer = "", stack1, helper;
  buffer += "\n                                    <tr class=\"small\">\n                                        <td>";
  if (helper = helpers.term) { stack1 = helper.call(depth0, {hash:{},data:data}); }
  else { helper = (depth0 && depth0.term); stack1 = typeof helper === functionType ? helper.call(depth0, {hash:{},data:data}) : helper; }
  buffer += escapeExpression(stack1)
    + "</td>\n                                        <td class=\"text-right\">";
  if (helper = helpers.Concepts) { stack1 = helper.call(depth0, {hash:{},data:data}); }
  else { helper = (depth0 && depth0.Concepts); stack1 = typeof helper === functionType ? helper.call(depth0, {hash:{},data:data}) : helper; }
  buffer += escapeExpression(stack1)
    + "</td>\n                                        <td class=\"text-right\">";
  if (helper = helpers.PercentOfTotal) { stack1 = helper.call(depth0, {hash:{},data:data}); }
  else { helper = (depth0 && depth0.PercentOfTotal); stack1 = typeof helper === functionType ? helper.call(depth0, {hash:{},data:data}) : helper; }
  buffer += escapeExpression(stack1)
    + "%</td>\n                                        <td class=\"text-right\">";
  if (helper = helpers.PercentOfSD) { stack1 = helper.call(depth0, {hash:{},data:data}); }
  else { helper = (depth0 && depth0.PercentOfSD); stack1 = typeof helper === functionType ? helper.call(depth0, {hash:{},data:data}) : helper; }
  buffer += escapeExpression(stack1)
    + "%</td>\n                                        <td class=\"text-right\">";
  if (helper = helpers.Descriptions) { stack1 = helper.call(depth0, {hash:{},data:data}); }
  else { helper = (depth0 && depth0.Descriptions); stack1 = typeof helper === functionType ? helper.call(depth0, {hash:{},data:data}) : helper; }
  buffer += escapeExpression(stack1)
    + "</td>\n                                        <td class=\"text-right\">";
  if (helper = helpers.Relationships) { stack1 = helper.call(depth0, {hash:{},data:data}); }
  else { helper = (depth0 && depth0.Relationships); stack1 = typeof helper === functionType ? helper.call(depth0, {hash:{},data:data}) : helper; }
  buffer += escapeExpression(stack1)
    + "</td>\n                                    </tr>\n                                ";
  return buffer;
  }

  buffer += "<div class=\"col-md-12\">\n    <div class=\"card\">\n        <div class=\"card-height-indicator\"></div>\n        <div class=\"card-content\">\n            <div class=\"card-body\">\n                <h2>General Release Statistics</h2>\n                <div class=\"row\">\n                    <div class=\"col-lg-12\">\n                        <div class=\"bs-component\">\n                            <table class=\"table table-striped table-hover \">\n                                <thead>\n                                <tr>\n                                    <th>Hierarchy</th>\n                                    <th class=\"text-right\">Concepts</th>\n                                    <th class=\"text-right\">% of SCT</th>\n                                    <th class=\"text-right\">% SD <small>(*)</small></th>\n                                    <th class=\"text-right\">Descriptions</th>\n                                    <th class=\"text-right\">Relationships</th>\n                                </tr>\n                                </thead>\n                                <tbody>\n                                ";
  stack1 = helpers.each.call(depth0, (depth0 && depth0.rows), {hash:{},inverse:self.noop,fn:self.program(1, program1, data),data:data});
  if(stack1 || stack1 === 0) { buffer += stack1; }
  buffer += "\n                                </tbody>\n                                <tfoot>\n                                    <tr>\n                                        <td colspan=\"6\">(*) Percentage of existing concepts in hierarchy which are Sufficiently Defined (SD). For the root concept the value includes all SNOMED CT.<br>\n                                        </td>\n                                    </tr>\n                                </tfoot>\n                            </table>\n                        </div><!-- /example -->\n                    </div>\n                </div>\n            </div>\n            <footer class=\"card-footer\">\n                <!--<button class=\"btn btn-flat\">Share</button>-->\n                <!--<button class=\"btn btn-flat btn-warning\">Learn More</button>-->\n            </footer>\n        </div>\n    </div>\n</div>\n";
  return buffer;
  });

this["JST"]["views/home.hbs"] = Handlebars.template(function (Handlebars,depth0,helpers,partials,data) {
  this.compilerInfo = [4,'>= 1.0.0'];
helpers = this.merge(helpers, Handlebars.helpers); data = data || {};
  


  return "<div class=\"col-md-11\">\n    <div class=\"jumbotron\">\n        <h1>SNOMED CT Descriptive Statistics</h1>\n        <p class=\"lead\">For 20160131 Release</p>\n        <p>This viewer presents a set of descriptive measures and statistics, obtained through automated queries, with the objective of providing information on content changes and temporal trends as a measurement of progress.</p>\n        <p><a class=\"btn btn-material-light-blue btn-lg\" href=\"javascript:loadOverview();\" role=\"button\">Learn more</a></p>\n        <div class=\"row\">\n            <div id=\"pieChart\"></div>\n        </div>\n        <div class=\"row\">\n            <div id=\"pieChart2\"></div>\n        </div>\n    </div>\n</div>\n";
  });

this["JST"]["views/newConcepts.hbs"] = Handlebars.template(function (Handlebars,depth0,helpers,partials,data) {
  this.compilerInfo = [4,'>= 1.0.0'];
helpers = this.merge(helpers, Handlebars.helpers); data = data || {};
  var buffer = "", stack1, functionType="function", escapeExpression=this.escapeExpression, self=this;

function program1(depth0,data) {
  
  var buffer = "", stack1, helper;
  buffer += "\n                                    <tr class=\"small\">\n                                        <td>";
  if (helper = helpers.term) { stack1 = helper.call(depth0, {hash:{},data:data}); }
  else { helper = (depth0 && depth0.term); stack1 = typeof helper === functionType ? helper.call(depth0, {hash:{},data:data}) : helper; }
  buffer += escapeExpression(stack1)
    + "</td>\n                                        <td class=\"text-right\">";
  if (helper = helpers.NewConcepts) { stack1 = helper.call(depth0, {hash:{},data:data}); }
  else { helper = (depth0 && depth0.NewConcepts); stack1 = typeof helper === functionType ? helper.call(depth0, {hash:{},data:data}) : helper; }
  buffer += escapeExpression(stack1)
    + "</td>\n                                        <td class=\"text-right\">";
  if (helper = helpers.PercentOfTotal) { stack1 = helper.call(depth0, {hash:{},data:data}); }
  else { helper = (depth0 && depth0.PercentOfTotal); stack1 = typeof helper === functionType ? helper.call(depth0, {hash:{},data:data}) : helper; }
  buffer += escapeExpression(stack1)
    + "%</td>\n                                        <td class=\"text-right\">";
  if (helper = helpers.PercentOfAllNew) { stack1 = helper.call(depth0, {hash:{},data:data}); }
  else { helper = (depth0 && depth0.PercentOfAllNew); stack1 = typeof helper === functionType ? helper.call(depth0, {hash:{},data:data}) : helper; }
  buffer += escapeExpression(stack1)
    + "%</td>\n                                        <td class=\"text-right\">";
  if (helper = helpers.PercentOfNewSD) { stack1 = helper.call(depth0, {hash:{},data:data}); }
  else { helper = (depth0 && depth0.PercentOfNewSD); stack1 = typeof helper === functionType ? helper.call(depth0, {hash:{},data:data}) : helper; }
  buffer += escapeExpression(stack1)
    + "%</td>\n                                        <td class=\"text-right\">";
  if (helper = helpers.PercentOfNewP) { stack1 = helper.call(depth0, {hash:{},data:data}); }
  else { helper = (depth0 && depth0.PercentOfNewP); stack1 = typeof helper === functionType ? helper.call(depth0, {hash:{},data:data}) : helper; }
  buffer += escapeExpression(stack1)
    + "%</td>\n                                    </tr>\n                                ";
  return buffer;
  }

  buffer += "<div class=\"col-md-12\">\n    <div class=\"card\">\n        <div class=\"card-height-indicator\"></div>\n        <div class=\"card-content\">\n            <div class=\"card-body\">\n                <h2>New Concepts Statistics</h2>\n                <div class=\"row\">\n                    <div class=\"col-lg-12\">\n                        <div class=\"bs-component\">\n                            <table class=\"table table-striped table-hover \">\n                                <thead>\n                                <tr>\n                                    <th>Hierarchy</th>\n                                    <th class=\"text-right\">New Concepts</th>\n                                    <th class=\"text-right\">% New in Hierarchy</th>\n                                    <th class=\"text-right\">% of All New in Release</th>\n                                    <th class=\"text-right\">% Added as SD (*)</th>\n                                    <th class=\"text-right\">% Added as P (*)</th>\n                                </tr>\n                                </thead>\n                                <tbody>\n                                ";
  stack1 = helpers.each.call(depth0, (depth0 && depth0.rows), {hash:{},inverse:self.noop,fn:self.program(1, program1, data),data:data});
  if(stack1 || stack1 === 0) { buffer += stack1; }
  buffer += "\n                                </tbody>\n                                <tfoot>\n                                    <tr>\n                                        <td colspan=\"6\">(*) SD = Sufficiently defined<br>\n                                            P = Primitive</td>\n                                    </tr>\n                                </tfoot>\n                            </table>\n                        </div><!-- /example -->\n                    </div>\n                </div>\n            </div>\n            <footer class=\"card-footer\">\n                <!--<button class=\"btn btn-flat\">Share</button>-->\n                <!--<button class=\"btn btn-flat btn-warning\">Learn More</button>-->\n            </footer>\n        </div>\n    </div>\n</div>\n";
  return buffer;
  });

this["JST"]["views/overview.hbs"] = Handlebars.template(function (Handlebars,depth0,helpers,partials,data) {
  this.compilerInfo = [4,'>= 1.0.0'];
helpers = this.merge(helpers, Handlebars.helpers); data = data || {};
  


  return "<div class=\"col-md-12\">\n    <div class=\"panel panel-default\">\n        <div class=\"panel-body\" id=\"overview-body\">\n        </div>\n    </div>\n</div>\n";
  });

this["JST"]["views/patternResults.hbs"] = Handlebars.template(function (Handlebars,depth0,helpers,partials,data) {
  this.compilerInfo = [4,'>= 1.0.0'];
helpers = this.merge(helpers, Handlebars.helpers); data = data || {};
  var buffer = "", stack1, helper, functionType="function", escapeExpression=this.escapeExpression, self=this;

function program1(depth0,data) {
  
  var buffer = "", stack1, helper;
  buffer += "\n                                    <tr class=\"small\">\n                                        <td class=\"\">";
  if (helper = helpers.conceptId) { stack1 = helper.call(depth0, {hash:{},data:data}); }
  else { helper = (depth0 && depth0.conceptId); stack1 = typeof helper === functionType ? helper.call(depth0, {hash:{},data:data}) : helper; }
  buffer += escapeExpression(stack1)
    + "</td>\n                                        <td class=\"\">";
  if (helper = helpers.term) { stack1 = helper.call(depth0, {hash:{},data:data}); }
  else { helper = (depth0 && depth0.term); stack1 = typeof helper === functionType ? helper.call(depth0, {hash:{},data:data}) : helper; }
  buffer += escapeExpression(stack1)
    + "</td>\n                                        <td class=\"\">";
  if (helper = helpers.matchDescription) { stack1 = helper.call(depth0, {hash:{},data:data}); }
  else { helper = (depth0 && depth0.matchDescription); stack1 = typeof helper === functionType ? helper.call(depth0, {hash:{},data:data}) : helper; }
  buffer += escapeExpression(stack1)
    + "</td>\n                                        <td class=\"\">\n                                        	pre-existing: ";
  stack1 = helpers['if'].call(depth0, (depth0 && depth0.preexisting), {hash:{},inverse:self.program(4, program4, data),fn:self.program(2, program2, data),data:data});
  if(stack1 || stack1 === 0) { buffer += stack1; }
  buffer += " <br>\n                                        	";
  stack1 = helpers['if'].call(depth0, (depth0 && depth0.matchObject), {hash:{},inverse:self.noop,fn:self.program(6, program6, data),data:data});
  if(stack1 || stack1 === 0) { buffer += stack1; }
  buffer += "\n                                        	new concept: ";
  stack1 = helpers['if'].call(depth0, (depth0 && depth0.isNew), {hash:{},inverse:self.program(4, program4, data),fn:self.program(2, program2, data),data:data});
  if(stack1 || stack1 === 0) { buffer += stack1; }
  buffer += "<br>\n                                        	modified concept: ";
  stack1 = helpers['if'].call(depth0, (depth0 && depth0.changed), {hash:{},inverse:self.program(4, program4, data),fn:self.program(2, program2, data),data:data});
  if(stack1 || stack1 === 0) { buffer += stack1; }
  buffer += "<br>\n                                        	tag: ";
  if (helper = helpers.semtag) { stack1 = helper.call(depth0, {hash:{},data:data}); }
  else { helper = (depth0 && depth0.semtag); stack1 = typeof helper === functionType ? helper.call(depth0, {hash:{},data:data}) : helper; }
  buffer += escapeExpression(stack1)
    + "\n                                    	</td>\n                                    </tr>\n                                ";
  return buffer;
  }
function program2(depth0,data) {
  
  
  return " yes ";
  }

function program4(depth0,data) {
  
  
  return " no";
  }

function program6(depth0,data) {
  
  var buffer = "", stack1;
  buffer += "\n                                        		";
  stack1 = helpers['if'].call(depth0, ((stack1 = (depth0 && depth0.matchObject)),stack1 == null || stack1 === false ? stack1 : stack1.Group1), {hash:{},inverse:self.noop,fn:self.program(7, program7, data),data:data});
  if(stack1 || stack1 === 0) { buffer += stack1; }
  buffer += "\n                                        	";
  return buffer;
  }
function program7(depth0,data) {
  
  var buffer = "", stack1;
  buffer += "\n    												def.status: "
    + escapeExpression(((stack1 = ((stack1 = ((stack1 = ((stack1 = (depth0 && depth0.matchObject)),stack1 == null || stack1 === false ? stack1 : stack1.Group1)),stack1 == null || stack1 === false ? stack1 : stack1[0])),stack1 == null || stack1 === false ? stack1 : stack1.definitionStatus)),typeof stack1 === functionType ? stack1.apply(depth0) : stack1))
    + "<br>	                                    		\n	                                        	";
  return buffer;
  }

  buffer += "<div class=\"col-md-12\">\n    <div class=\"card\" style=\"overflow: auto;\">\n        <div class=\"card-height-indicator\"></div>\n        <div class=\"card-content\">\n            <div class=\"card-body\">\n                <h2>Pattern: ";
  if (helper = helpers.pattern) { stack1 = helper.call(depth0, {hash:{},data:data}); }
  else { helper = (depth0 && depth0.pattern); stack1 = typeof helper === functionType ? helper.call(depth0, {hash:{},data:data}) : helper; }
  buffer += escapeExpression(stack1)
    + "</h2>\n                <p>";
  if (helper = helpers.description) { stack1 = helper.call(depth0, {hash:{},data:data}); }
  else { helper = (depth0 && depth0.description); stack1 = typeof helper === functionType ? helper.call(depth0, {hash:{},data:data}) : helper; }
  buffer += escapeExpression(stack1)
    + "</p>\n                <p>";
  if (helper = helpers.count) { stack1 = helper.call(depth0, {hash:{},data:data}); }
  else { helper = (depth0 && depth0.count); stack1 = typeof helper === functionType ? helper.call(depth0, {hash:{},data:data}) : helper; }
  buffer += escapeExpression(stack1)
    + " concepts</p>\n                \n                <div class=\"navbar-collapse collapse navbar-responsive-collapse\">\n                	<ul class=\"nav navbar-nav\">\n                		<li id=\"tab-pattern-sort\" class=\"dropdown\">\n                                <a href=\"\" data-target=\"#\" class=\"btn btn-primary btn-sm dropdown-toggle\" data-toggle=\"dropdown\">Sort by <b class=\"caret\"></b></a>\n                                <ul class=\"dropdown-menu\">\n                                    <li><a href=\"javascript:loadFullResult('";
  if (helper = helpers.file) { stack1 = helper.call(depth0, {hash:{},data:data}); }
  else { helper = (depth0 && depth0.file); stack1 = typeof helper === functionType ? helper.call(depth0, {hash:{},data:data}) : helper; }
  buffer += escapeExpression(stack1)
    + "','";
  if (helper = helpers.pattern) { stack1 = helper.call(depth0, {hash:{},data:data}); }
  else { helper = (depth0 && depth0.pattern); stack1 = typeof helper === functionType ? helper.call(depth0, {hash:{},data:data}) : helper; }
  buffer += escapeExpression(stack1)
    + "','";
  if (helper = helpers.description) { stack1 = helper.call(depth0, {hash:{},data:data}); }
  else { helper = (depth0 && depth0.description); stack1 = typeof helper === functionType ? helper.call(depth0, {hash:{},data:data}) : helper; }
  buffer += escapeExpression(stack1)
    + "','conceptId','";
  if (helper = helpers.filterPreexist) { stack1 = helper.call(depth0, {hash:{},data:data}); }
  else { helper = (depth0 && depth0.filterPreexist); stack1 = typeof helper === functionType ? helper.call(depth0, {hash:{},data:data}) : helper; }
  buffer += escapeExpression(stack1)
    + "','";
  if (helper = helpers.filterNewCpt) { stack1 = helper.call(depth0, {hash:{},data:data}); }
  else { helper = (depth0 && depth0.filterNewCpt); stack1 = typeof helper === functionType ? helper.call(depth0, {hash:{},data:data}) : helper; }
  buffer += escapeExpression(stack1)
    + "','";
  if (helper = helpers.filterModCpt) { stack1 = helper.call(depth0, {hash:{},data:data}); }
  else { helper = (depth0 && depth0.filterModCpt); stack1 = typeof helper === functionType ? helper.call(depth0, {hash:{},data:data}) : helper; }
  buffer += escapeExpression(stack1)
    + "')\">Concept ID</a></li>\n                                    <li><a href=\"javascript:loadFullResult('";
  if (helper = helpers.file) { stack1 = helper.call(depth0, {hash:{},data:data}); }
  else { helper = (depth0 && depth0.file); stack1 = typeof helper === functionType ? helper.call(depth0, {hash:{},data:data}) : helper; }
  buffer += escapeExpression(stack1)
    + "','";
  if (helper = helpers.pattern) { stack1 = helper.call(depth0, {hash:{},data:data}); }
  else { helper = (depth0 && depth0.pattern); stack1 = typeof helper === functionType ? helper.call(depth0, {hash:{},data:data}) : helper; }
  buffer += escapeExpression(stack1)
    + "','";
  if (helper = helpers.description) { stack1 = helper.call(depth0, {hash:{},data:data}); }
  else { helper = (depth0 && depth0.description); stack1 = typeof helper === functionType ? helper.call(depth0, {hash:{},data:data}) : helper; }
  buffer += escapeExpression(stack1)
    + "','term','";
  if (helper = helpers.filterPreexist) { stack1 = helper.call(depth0, {hash:{},data:data}); }
  else { helper = (depth0 && depth0.filterPreexist); stack1 = typeof helper === functionType ? helper.call(depth0, {hash:{},data:data}) : helper; }
  buffer += escapeExpression(stack1)
    + "','";
  if (helper = helpers.filterNewCpt) { stack1 = helper.call(depth0, {hash:{},data:data}); }
  else { helper = (depth0 && depth0.filterNewCpt); stack1 = typeof helper === functionType ? helper.call(depth0, {hash:{},data:data}) : helper; }
  buffer += escapeExpression(stack1)
    + "','";
  if (helper = helpers.filterModCpt) { stack1 = helper.call(depth0, {hash:{},data:data}); }
  else { helper = (depth0 && depth0.filterModCpt); stack1 = typeof helper === functionType ? helper.call(depth0, {hash:{},data:data}) : helper; }
  buffer += escapeExpression(stack1)
    + "')\">Term</a></li>\n                                    <li><a href=\"javascript:loadFullResult('";
  if (helper = helpers.file) { stack1 = helper.call(depth0, {hash:{},data:data}); }
  else { helper = (depth0 && depth0.file); stack1 = typeof helper === functionType ? helper.call(depth0, {hash:{},data:data}) : helper; }
  buffer += escapeExpression(stack1)
    + "','";
  if (helper = helpers.pattern) { stack1 = helper.call(depth0, {hash:{},data:data}); }
  else { helper = (depth0 && depth0.pattern); stack1 = typeof helper === functionType ? helper.call(depth0, {hash:{},data:data}) : helper; }
  buffer += escapeExpression(stack1)
    + "','";
  if (helper = helpers.description) { stack1 = helper.call(depth0, {hash:{},data:data}); }
  else { helper = (depth0 && depth0.description); stack1 = typeof helper === functionType ? helper.call(depth0, {hash:{},data:data}) : helper; }
  buffer += escapeExpression(stack1)
    + "','matchDescription','";
  if (helper = helpers.filterPreexist) { stack1 = helper.call(depth0, {hash:{},data:data}); }
  else { helper = (depth0 && depth0.filterPreexist); stack1 = typeof helper === functionType ? helper.call(depth0, {hash:{},data:data}) : helper; }
  buffer += escapeExpression(stack1)
    + "','";
  if (helper = helpers.filterNewCpt) { stack1 = helper.call(depth0, {hash:{},data:data}); }
  else { helper = (depth0 && depth0.filterNewCpt); stack1 = typeof helper === functionType ? helper.call(depth0, {hash:{},data:data}) : helper; }
  buffer += escapeExpression(stack1)
    + "','";
  if (helper = helpers.filterModCpt) { stack1 = helper.call(depth0, {hash:{},data:data}); }
  else { helper = (depth0 && depth0.filterModCpt); stack1 = typeof helper === functionType ? helper.call(depth0, {hash:{},data:data}) : helper; }
  buffer += escapeExpression(stack1)
    + "')\">Explanation</a></li>\n                                    <li><a href=\"javascript:loadFullResult('";
  if (helper = helpers.file) { stack1 = helper.call(depth0, {hash:{},data:data}); }
  else { helper = (depth0 && depth0.file); stack1 = typeof helper === functionType ? helper.call(depth0, {hash:{},data:data}) : helper; }
  buffer += escapeExpression(stack1)
    + "','";
  if (helper = helpers.pattern) { stack1 = helper.call(depth0, {hash:{},data:data}); }
  else { helper = (depth0 && depth0.pattern); stack1 = typeof helper === functionType ? helper.call(depth0, {hash:{},data:data}) : helper; }
  buffer += escapeExpression(stack1)
    + "','";
  if (helper = helpers.description) { stack1 = helper.call(depth0, {hash:{},data:data}); }
  else { helper = (depth0 && depth0.description); stack1 = typeof helper === functionType ? helper.call(depth0, {hash:{},data:data}) : helper; }
  buffer += escapeExpression(stack1)
    + "','semtag','";
  if (helper = helpers.filterPreexist) { stack1 = helper.call(depth0, {hash:{},data:data}); }
  else { helper = (depth0 && depth0.filterPreexist); stack1 = typeof helper === functionType ? helper.call(depth0, {hash:{},data:data}) : helper; }
  buffer += escapeExpression(stack1)
    + "','";
  if (helper = helpers.filterNewCpt) { stack1 = helper.call(depth0, {hash:{},data:data}); }
  else { helper = (depth0 && depth0.filterNewCpt); stack1 = typeof helper === functionType ? helper.call(depth0, {hash:{},data:data}) : helper; }
  buffer += escapeExpression(stack1)
    + "','";
  if (helper = helpers.filterModCpt) { stack1 = helper.call(depth0, {hash:{},data:data}); }
  else { helper = (depth0 && depth0.filterModCpt); stack1 = typeof helper === functionType ? helper.call(depth0, {hash:{},data:data}) : helper; }
  buffer += escapeExpression(stack1)
    + "')\">Semantic tag</a></li>\n                                </ul>\n                            </li>\n                 		<li id=\"tab-pattern-preexist-filter\" class=\"dropdown\">\n                                <a href=\"\" data-target=\"#\" class=\"btn btn-primary btn-sm dropdown-toggle\" data-toggle=\"dropdown\">Filter by Pre-existing: <b class=\"caret\"></b></a>\n                                <ul class=\"dropdown-menu\">\n                                    <li><a href=\"javascript:loadFullResult('";
  if (helper = helpers.file) { stack1 = helper.call(depth0, {hash:{},data:data}); }
  else { helper = (depth0 && depth0.file); stack1 = typeof helper === functionType ? helper.call(depth0, {hash:{},data:data}) : helper; }
  buffer += escapeExpression(stack1)
    + "','";
  if (helper = helpers.pattern) { stack1 = helper.call(depth0, {hash:{},data:data}); }
  else { helper = (depth0 && depth0.pattern); stack1 = typeof helper === functionType ? helper.call(depth0, {hash:{},data:data}) : helper; }
  buffer += escapeExpression(stack1)
    + "','";
  if (helper = helpers.description) { stack1 = helper.call(depth0, {hash:{},data:data}); }
  else { helper = (depth0 && depth0.description); stack1 = typeof helper === functionType ? helper.call(depth0, {hash:{},data:data}) : helper; }
  buffer += escapeExpression(stack1)
    + "','";
  if (helper = helpers.sortBy) { stack1 = helper.call(depth0, {hash:{},data:data}); }
  else { helper = (depth0 && depth0.sortBy); stack1 = typeof helper === functionType ? helper.call(depth0, {hash:{},data:data}) : helper; }
  buffer += escapeExpression(stack1)
    + "','true','";
  if (helper = helpers.filterNewCpt) { stack1 = helper.call(depth0, {hash:{},data:data}); }
  else { helper = (depth0 && depth0.filterNewCpt); stack1 = typeof helper === functionType ? helper.call(depth0, {hash:{},data:data}) : helper; }
  buffer += escapeExpression(stack1)
    + "','";
  if (helper = helpers.filterModCpt) { stack1 = helper.call(depth0, {hash:{},data:data}); }
  else { helper = (depth0 && depth0.filterModCpt); stack1 = typeof helper === functionType ? helper.call(depth0, {hash:{},data:data}) : helper; }
  buffer += escapeExpression(stack1)
    + "')\">Yes</a></li>\n                                    <li><a href=\"javascript:loadFullResult('";
  if (helper = helpers.file) { stack1 = helper.call(depth0, {hash:{},data:data}); }
  else { helper = (depth0 && depth0.file); stack1 = typeof helper === functionType ? helper.call(depth0, {hash:{},data:data}) : helper; }
  buffer += escapeExpression(stack1)
    + "','";
  if (helper = helpers.pattern) { stack1 = helper.call(depth0, {hash:{},data:data}); }
  else { helper = (depth0 && depth0.pattern); stack1 = typeof helper === functionType ? helper.call(depth0, {hash:{},data:data}) : helper; }
  buffer += escapeExpression(stack1)
    + "','";
  if (helper = helpers.description) { stack1 = helper.call(depth0, {hash:{},data:data}); }
  else { helper = (depth0 && depth0.description); stack1 = typeof helper === functionType ? helper.call(depth0, {hash:{},data:data}) : helper; }
  buffer += escapeExpression(stack1)
    + "','";
  if (helper = helpers.sortBy) { stack1 = helper.call(depth0, {hash:{},data:data}); }
  else { helper = (depth0 && depth0.sortBy); stack1 = typeof helper === functionType ? helper.call(depth0, {hash:{},data:data}) : helper; }
  buffer += escapeExpression(stack1)
    + "','false','";
  if (helper = helpers.filterNewCpt) { stack1 = helper.call(depth0, {hash:{},data:data}); }
  else { helper = (depth0 && depth0.filterNewCpt); stack1 = typeof helper === functionType ? helper.call(depth0, {hash:{},data:data}) : helper; }
  buffer += escapeExpression(stack1)
    + "','";
  if (helper = helpers.filterModCpt) { stack1 = helper.call(depth0, {hash:{},data:data}); }
  else { helper = (depth0 && depth0.filterModCpt); stack1 = typeof helper === functionType ? helper.call(depth0, {hash:{},data:data}) : helper; }
  buffer += escapeExpression(stack1)
    + "')\">No</a></li>\n                                    <li><a href=\"javascript:loadFullResult('";
  if (helper = helpers.file) { stack1 = helper.call(depth0, {hash:{},data:data}); }
  else { helper = (depth0 && depth0.file); stack1 = typeof helper === functionType ? helper.call(depth0, {hash:{},data:data}) : helper; }
  buffer += escapeExpression(stack1)
    + "','";
  if (helper = helpers.pattern) { stack1 = helper.call(depth0, {hash:{},data:data}); }
  else { helper = (depth0 && depth0.pattern); stack1 = typeof helper === functionType ? helper.call(depth0, {hash:{},data:data}) : helper; }
  buffer += escapeExpression(stack1)
    + "','";
  if (helper = helpers.description) { stack1 = helper.call(depth0, {hash:{},data:data}); }
  else { helper = (depth0 && depth0.description); stack1 = typeof helper === functionType ? helper.call(depth0, {hash:{},data:data}) : helper; }
  buffer += escapeExpression(stack1)
    + "','";
  if (helper = helpers.sortBy) { stack1 = helper.call(depth0, {hash:{},data:data}); }
  else { helper = (depth0 && depth0.sortBy); stack1 = typeof helper === functionType ? helper.call(depth0, {hash:{},data:data}) : helper; }
  buffer += escapeExpression(stack1)
    + "','','";
  if (helper = helpers.filterNewCpt) { stack1 = helper.call(depth0, {hash:{},data:data}); }
  else { helper = (depth0 && depth0.filterNewCpt); stack1 = typeof helper === functionType ? helper.call(depth0, {hash:{},data:data}) : helper; }
  buffer += escapeExpression(stack1)
    + "','";
  if (helper = helpers.filterModCpt) { stack1 = helper.call(depth0, {hash:{},data:data}); }
  else { helper = (depth0 && depth0.filterModCpt); stack1 = typeof helper === functionType ? helper.call(depth0, {hash:{},data:data}) : helper; }
  buffer += escapeExpression(stack1)
    + "')\">All</a></li>\n                                </ul>\n                            </li>\n                 		<li id=\"tab-pattern-newcpt-filter\" class=\"dropdown\">\n                                <a href=\"\" data-target=\"#\" class=\"btn btn-primary btn-sm dropdown-toggle\" data-toggle=\"dropdown\">Filter by New concept: <b class=\"caret\"></b></a>\n                                <ul class=\"dropdown-menu\">\n                                    <li><a href=\"javascript:loadFullResult('";
  if (helper = helpers.file) { stack1 = helper.call(depth0, {hash:{},data:data}); }
  else { helper = (depth0 && depth0.file); stack1 = typeof helper === functionType ? helper.call(depth0, {hash:{},data:data}) : helper; }
  buffer += escapeExpression(stack1)
    + "','";
  if (helper = helpers.pattern) { stack1 = helper.call(depth0, {hash:{},data:data}); }
  else { helper = (depth0 && depth0.pattern); stack1 = typeof helper === functionType ? helper.call(depth0, {hash:{},data:data}) : helper; }
  buffer += escapeExpression(stack1)
    + "','";
  if (helper = helpers.description) { stack1 = helper.call(depth0, {hash:{},data:data}); }
  else { helper = (depth0 && depth0.description); stack1 = typeof helper === functionType ? helper.call(depth0, {hash:{},data:data}) : helper; }
  buffer += escapeExpression(stack1)
    + "','";
  if (helper = helpers.sortBy) { stack1 = helper.call(depth0, {hash:{},data:data}); }
  else { helper = (depth0 && depth0.sortBy); stack1 = typeof helper === functionType ? helper.call(depth0, {hash:{},data:data}) : helper; }
  buffer += escapeExpression(stack1)
    + "','";
  if (helper = helpers.filterPreexist) { stack1 = helper.call(depth0, {hash:{},data:data}); }
  else { helper = (depth0 && depth0.filterPreexist); stack1 = typeof helper === functionType ? helper.call(depth0, {hash:{},data:data}) : helper; }
  buffer += escapeExpression(stack1)
    + "','true','";
  if (helper = helpers.filterModCpt) { stack1 = helper.call(depth0, {hash:{},data:data}); }
  else { helper = (depth0 && depth0.filterModCpt); stack1 = typeof helper === functionType ? helper.call(depth0, {hash:{},data:data}) : helper; }
  buffer += escapeExpression(stack1)
    + "')\">Yes</a></li>\n                                    <li><a href=\"javascript:loadFullResult('";
  if (helper = helpers.file) { stack1 = helper.call(depth0, {hash:{},data:data}); }
  else { helper = (depth0 && depth0.file); stack1 = typeof helper === functionType ? helper.call(depth0, {hash:{},data:data}) : helper; }
  buffer += escapeExpression(stack1)
    + "','";
  if (helper = helpers.pattern) { stack1 = helper.call(depth0, {hash:{},data:data}); }
  else { helper = (depth0 && depth0.pattern); stack1 = typeof helper === functionType ? helper.call(depth0, {hash:{},data:data}) : helper; }
  buffer += escapeExpression(stack1)
    + "','";
  if (helper = helpers.description) { stack1 = helper.call(depth0, {hash:{},data:data}); }
  else { helper = (depth0 && depth0.description); stack1 = typeof helper === functionType ? helper.call(depth0, {hash:{},data:data}) : helper; }
  buffer += escapeExpression(stack1)
    + "','";
  if (helper = helpers.sortBy) { stack1 = helper.call(depth0, {hash:{},data:data}); }
  else { helper = (depth0 && depth0.sortBy); stack1 = typeof helper === functionType ? helper.call(depth0, {hash:{},data:data}) : helper; }
  buffer += escapeExpression(stack1)
    + "','";
  if (helper = helpers.filterPreexist) { stack1 = helper.call(depth0, {hash:{},data:data}); }
  else { helper = (depth0 && depth0.filterPreexist); stack1 = typeof helper === functionType ? helper.call(depth0, {hash:{},data:data}) : helper; }
  buffer += escapeExpression(stack1)
    + "','false','";
  if (helper = helpers.filterModCpt) { stack1 = helper.call(depth0, {hash:{},data:data}); }
  else { helper = (depth0 && depth0.filterModCpt); stack1 = typeof helper === functionType ? helper.call(depth0, {hash:{},data:data}) : helper; }
  buffer += escapeExpression(stack1)
    + "')\">No</a></li>\n                                    <li><a href=\"javascript:loadFullResult('";
  if (helper = helpers.file) { stack1 = helper.call(depth0, {hash:{},data:data}); }
  else { helper = (depth0 && depth0.file); stack1 = typeof helper === functionType ? helper.call(depth0, {hash:{},data:data}) : helper; }
  buffer += escapeExpression(stack1)
    + "','";
  if (helper = helpers.pattern) { stack1 = helper.call(depth0, {hash:{},data:data}); }
  else { helper = (depth0 && depth0.pattern); stack1 = typeof helper === functionType ? helper.call(depth0, {hash:{},data:data}) : helper; }
  buffer += escapeExpression(stack1)
    + "','";
  if (helper = helpers.description) { stack1 = helper.call(depth0, {hash:{},data:data}); }
  else { helper = (depth0 && depth0.description); stack1 = typeof helper === functionType ? helper.call(depth0, {hash:{},data:data}) : helper; }
  buffer += escapeExpression(stack1)
    + "','";
  if (helper = helpers.sortBy) { stack1 = helper.call(depth0, {hash:{},data:data}); }
  else { helper = (depth0 && depth0.sortBy); stack1 = typeof helper === functionType ? helper.call(depth0, {hash:{},data:data}) : helper; }
  buffer += escapeExpression(stack1)
    + "','";
  if (helper = helpers.filterPreexist) { stack1 = helper.call(depth0, {hash:{},data:data}); }
  else { helper = (depth0 && depth0.filterPreexist); stack1 = typeof helper === functionType ? helper.call(depth0, {hash:{},data:data}) : helper; }
  buffer += escapeExpression(stack1)
    + "','','";
  if (helper = helpers.filterModCpt) { stack1 = helper.call(depth0, {hash:{},data:data}); }
  else { helper = (depth0 && depth0.filterModCpt); stack1 = typeof helper === functionType ? helper.call(depth0, {hash:{},data:data}) : helper; }
  buffer += escapeExpression(stack1)
    + "')\">All</a></li>\n                                </ul>\n                            </li>\n                 		<li id=\"tab-pattern-modcpt-filter\" class=\"dropdown\">\n                                <a href=\"\" data-target=\"#\" class=\"btn btn-primary btn-sm dropdown-toggle\" data-toggle=\"dropdown\">Filter by Modified concept: <b class=\"caret\"></b></a>\n                                <ul class=\"dropdown-menu\">\n                                    <li><a href=\"javascript:loadFullResult('";
  if (helper = helpers.file) { stack1 = helper.call(depth0, {hash:{},data:data}); }
  else { helper = (depth0 && depth0.file); stack1 = typeof helper === functionType ? helper.call(depth0, {hash:{},data:data}) : helper; }
  buffer += escapeExpression(stack1)
    + "','";
  if (helper = helpers.pattern) { stack1 = helper.call(depth0, {hash:{},data:data}); }
  else { helper = (depth0 && depth0.pattern); stack1 = typeof helper === functionType ? helper.call(depth0, {hash:{},data:data}) : helper; }
  buffer += escapeExpression(stack1)
    + "','";
  if (helper = helpers.description) { stack1 = helper.call(depth0, {hash:{},data:data}); }
  else { helper = (depth0 && depth0.description); stack1 = typeof helper === functionType ? helper.call(depth0, {hash:{},data:data}) : helper; }
  buffer += escapeExpression(stack1)
    + "','";
  if (helper = helpers.sortBy) { stack1 = helper.call(depth0, {hash:{},data:data}); }
  else { helper = (depth0 && depth0.sortBy); stack1 = typeof helper === functionType ? helper.call(depth0, {hash:{},data:data}) : helper; }
  buffer += escapeExpression(stack1)
    + "','";
  if (helper = helpers.filterPreexist) { stack1 = helper.call(depth0, {hash:{},data:data}); }
  else { helper = (depth0 && depth0.filterPreexist); stack1 = typeof helper === functionType ? helper.call(depth0, {hash:{},data:data}) : helper; }
  buffer += escapeExpression(stack1)
    + "','";
  if (helper = helpers.filterNewCpt) { stack1 = helper.call(depth0, {hash:{},data:data}); }
  else { helper = (depth0 && depth0.filterNewCpt); stack1 = typeof helper === functionType ? helper.call(depth0, {hash:{},data:data}) : helper; }
  buffer += escapeExpression(stack1)
    + "','true')\">Yes</a></li>\n                                    <li><a href=\"javascript:loadFullResult('";
  if (helper = helpers.file) { stack1 = helper.call(depth0, {hash:{},data:data}); }
  else { helper = (depth0 && depth0.file); stack1 = typeof helper === functionType ? helper.call(depth0, {hash:{},data:data}) : helper; }
  buffer += escapeExpression(stack1)
    + "','";
  if (helper = helpers.pattern) { stack1 = helper.call(depth0, {hash:{},data:data}); }
  else { helper = (depth0 && depth0.pattern); stack1 = typeof helper === functionType ? helper.call(depth0, {hash:{},data:data}) : helper; }
  buffer += escapeExpression(stack1)
    + "','";
  if (helper = helpers.description) { stack1 = helper.call(depth0, {hash:{},data:data}); }
  else { helper = (depth0 && depth0.description); stack1 = typeof helper === functionType ? helper.call(depth0, {hash:{},data:data}) : helper; }
  buffer += escapeExpression(stack1)
    + "','";
  if (helper = helpers.sortBy) { stack1 = helper.call(depth0, {hash:{},data:data}); }
  else { helper = (depth0 && depth0.sortBy); stack1 = typeof helper === functionType ? helper.call(depth0, {hash:{},data:data}) : helper; }
  buffer += escapeExpression(stack1)
    + "','";
  if (helper = helpers.filterPreexist) { stack1 = helper.call(depth0, {hash:{},data:data}); }
  else { helper = (depth0 && depth0.filterPreexist); stack1 = typeof helper === functionType ? helper.call(depth0, {hash:{},data:data}) : helper; }
  buffer += escapeExpression(stack1)
    + "','";
  if (helper = helpers.filterNewCpt) { stack1 = helper.call(depth0, {hash:{},data:data}); }
  else { helper = (depth0 && depth0.filterNewCpt); stack1 = typeof helper === functionType ? helper.call(depth0, {hash:{},data:data}) : helper; }
  buffer += escapeExpression(stack1)
    + "','false')\">No</a></li>\n                                    <li><a href=\"javascript:loadFullResult('";
  if (helper = helpers.file) { stack1 = helper.call(depth0, {hash:{},data:data}); }
  else { helper = (depth0 && depth0.file); stack1 = typeof helper === functionType ? helper.call(depth0, {hash:{},data:data}) : helper; }
  buffer += escapeExpression(stack1)
    + "','";
  if (helper = helpers.pattern) { stack1 = helper.call(depth0, {hash:{},data:data}); }
  else { helper = (depth0 && depth0.pattern); stack1 = typeof helper === functionType ? helper.call(depth0, {hash:{},data:data}) : helper; }
  buffer += escapeExpression(stack1)
    + "','";
  if (helper = helpers.description) { stack1 = helper.call(depth0, {hash:{},data:data}); }
  else { helper = (depth0 && depth0.description); stack1 = typeof helper === functionType ? helper.call(depth0, {hash:{},data:data}) : helper; }
  buffer += escapeExpression(stack1)
    + "','";
  if (helper = helpers.sortBy) { stack1 = helper.call(depth0, {hash:{},data:data}); }
  else { helper = (depth0 && depth0.sortBy); stack1 = typeof helper === functionType ? helper.call(depth0, {hash:{},data:data}) : helper; }
  buffer += escapeExpression(stack1)
    + "','";
  if (helper = helpers.filterPreexist) { stack1 = helper.call(depth0, {hash:{},data:data}); }
  else { helper = (depth0 && depth0.filterPreexist); stack1 = typeof helper === functionType ? helper.call(depth0, {hash:{},data:data}) : helper; }
  buffer += escapeExpression(stack1)
    + "','";
  if (helper = helpers.filterNewCpt) { stack1 = helper.call(depth0, {hash:{},data:data}); }
  else { helper = (depth0 && depth0.filterNewCpt); stack1 = typeof helper === functionType ? helper.call(depth0, {hash:{},data:data}) : helper; }
  buffer += escapeExpression(stack1)
    + "','')\">All</a></li>\n                                </ul>\n                            </li>\n                  	</ul>\n                </div>\n                <a class=\"btn btn-material-light-blue pull-right\" style=\"margin: 0px;\" href=\"javascript:loadAnalyticsResultsIndex();\" role=\"button\">Back to Patterns</a>\n                <div class=\"row\">\n                    <div class=\"col-lg-12\">\n                        <div class=\"bs-component\">\n                            <table class=\"table table-striped\">\n                                <thead>\n                                <tr>\n                                    <th class=\"col-md-1\">ConceptId</th>\n                                    <th class=\"col-md-4\">Term</th>\n                                    <th class=\"col-md-4\">Explanation</th>\n                                    <th class=\"col-md-3\"></th>\n                                </tr>\n                                </thead>\n                                <tbody>\n                                ";
  stack1 = helpers.each.call(depth0, (depth0 && depth0.results), {hash:{},inverse:self.noop,fn:self.program(1, program1, data),data:data});
  if(stack1 || stack1 === 0) { buffer += stack1; }
  buffer += "\n                                </tbody>\n                                <tfoot>\n                                    <tr>\n                                        <!--<td colspan=\"6\">(*) Percentage of existing concepts in hierarchy which are Sufficiently Defined (SD). For the root concept the value includes all SNOMED CT.<br>-->\n                                        </td>\n                                    </tr>\n                                </tfoot>\n                            </table>\n                        </div><!-- /example -->\n                    </div>\n                </div>\n            </div>\n            <footer class=\"card-footer\">\n                <!--<button class=\"btn btn-flat\">Share</button>-->\n                <!--<button class=\"btn btn-flat btn-warning\">Learn More</button>-->\n            </footer>\n        </div>\n    </div>\n</div>\n";
  return buffer;
  });

this["JST"]["views/pivot.hbs"] = Handlebars.template(function (Handlebars,depth0,helpers,partials,data) {
  this.compilerInfo = [4,'>= 1.0.0'];
helpers = this.merge(helpers, Handlebars.helpers); data = data || {};
  var buffer = "", stack1, helper, functionType="function", escapeExpression=this.escapeExpression;


  buffer += "<div class=\"col-md-12\">\n    <div class=\"card\">\n        <div class=\"card-height-indicator\"></div>\n        <div class=\"card-content\">\n            <div class=\"card-body\">\n                <div cass=\"container\">\n                    <h2>";
  if (helper = helpers.title) { stack1 = helper.call(depth0, {hash:{},data:data}); }
  else { helper = (depth0 && depth0.title); stack1 = typeof helper === functionType ? helper.call(depth0, {hash:{},data:data}) : helper; }
  buffer += escapeExpression(stack1)
    + "</h2>\n                    <div id=\"pivot\"><p><em>Loading data...</em></p></div>\n                </div>\n            </div>\n            <footer class=\"card-footer\">\n                <!--<button class=\"btn btn-flat\">Share</button>-->\n                <!--<button class=\"btn btn-flat btn-warning\">Learn More</button>-->\n            </footer>\n        </div>\n    </div>\n</div>\n";
  return buffer;
  });

this["JST"]["views/retiredConcepts.hbs"] = Handlebars.template(function (Handlebars,depth0,helpers,partials,data) {
  this.compilerInfo = [4,'>= 1.0.0'];
helpers = this.merge(helpers, Handlebars.helpers); data = data || {};
  var buffer = "", stack1, functionType="function", escapeExpression=this.escapeExpression, self=this;

function program1(depth0,data) {
  
  var buffer = "", stack1, helper;
  buffer += "\n                                    <tr class=\"small\">\n                                        <td>";
  if (helper = helpers.term) { stack1 = helper.call(depth0, {hash:{},data:data}); }
  else { helper = (depth0 && depth0.term); stack1 = typeof helper === functionType ? helper.call(depth0, {hash:{},data:data}) : helper; }
  buffer += escapeExpression(stack1)
    + "</td>\n                                        <td class=\"text-right\">";
  if (helper = helpers.RetiredConcepts) { stack1 = helper.call(depth0, {hash:{},data:data}); }
  else { helper = (depth0 && depth0.RetiredConcepts); stack1 = typeof helper === functionType ? helper.call(depth0, {hash:{},data:data}) : helper; }
  buffer += escapeExpression(stack1)
    + "</td>\n                                        <td class=\"text-right\">";
  if (helper = helpers.PercentOfTotal) { stack1 = helper.call(depth0, {hash:{},data:data}); }
  else { helper = (depth0 && depth0.PercentOfTotal); stack1 = typeof helper === functionType ? helper.call(depth0, {hash:{},data:data}) : helper; }
  buffer += escapeExpression(stack1)
    + "%</td>\n                                        <td class=\"text-right\">";
  if (helper = helpers.PercentOfAllRetired) { stack1 = helper.call(depth0, {hash:{},data:data}); }
  else { helper = (depth0 && depth0.PercentOfAllRetired); stack1 = typeof helper === functionType ? helper.call(depth0, {hash:{},data:data}) : helper; }
  buffer += escapeExpression(stack1)
    + "%</td>\n                                        <td class=\"text-right\">";
  if (helper = helpers.PercentOfAmbiguous) { stack1 = helper.call(depth0, {hash:{},data:data}); }
  else { helper = (depth0 && depth0.PercentOfAmbiguous); stack1 = typeof helper === functionType ? helper.call(depth0, {hash:{},data:data}) : helper; }
  buffer += escapeExpression(stack1)
    + "%</td>\n                                        <td class=\"text-right\">";
  if (helper = helpers.PercentOfDuplicated) { stack1 = helper.call(depth0, {hash:{},data:data}); }
  else { helper = (depth0 && depth0.PercentOfDuplicated); stack1 = typeof helper === functionType ? helper.call(depth0, {hash:{},data:data}) : helper; }
  buffer += escapeExpression(stack1)
    + "%</td>\n                                        <td class=\"text-right\">";
  if (helper = helpers.PercentOfErroneous) { stack1 = helper.call(depth0, {hash:{},data:data}); }
  else { helper = (depth0 && depth0.PercentOfErroneous); stack1 = typeof helper === functionType ? helper.call(depth0, {hash:{},data:data}) : helper; }
  buffer += escapeExpression(stack1)
    + "%</td>\n                                        <td class=\"text-right\">";
  if (helper = helpers.PercentOfNotStated) { stack1 = helper.call(depth0, {hash:{},data:data}); }
  else { helper = (depth0 && depth0.PercentOfNotStated); stack1 = typeof helper === functionType ? helper.call(depth0, {hash:{},data:data}) : helper; }
  buffer += escapeExpression(stack1)
    + "%</td>\n                                        <td class=\"text-right\">";
  if (helper = helpers.PercentOfOutdated) { stack1 = helper.call(depth0, {hash:{},data:data}); }
  else { helper = (depth0 && depth0.PercentOfOutdated); stack1 = typeof helper === functionType ? helper.call(depth0, {hash:{},data:data}) : helper; }
  buffer += escapeExpression(stack1)
    + "%</td>\n                                        <td class=\"text-right\">";
  if (helper = helpers.PercentOfMoved) { stack1 = helper.call(depth0, {hash:{},data:data}); }
  else { helper = (depth0 && depth0.PercentOfMoved); stack1 = typeof helper === functionType ? helper.call(depth0, {hash:{},data:data}) : helper; }
  buffer += escapeExpression(stack1)
    + "%</td>\n                                        <td class=\"text-right\">";
  if (helper = helpers.PercentOfLimited) { stack1 = helper.call(depth0, {hash:{},data:data}); }
  else { helper = (depth0 && depth0.PercentOfLimited); stack1 = typeof helper === functionType ? helper.call(depth0, {hash:{},data:data}) : helper; }
  buffer += escapeExpression(stack1)
    + "%</td>\n                                    </tr>\n                                ";
  return buffer;
  }

  buffer += "<div class=\"col-md-12\">\n    <div class=\"card\">\n        <div class=\"card-height-indicator\"></div>\n        <div class=\"card-content\">\n            <div class=\"card-body\">\n                <h2>Statistics on Concepts Inactivated During the Release</h2>\n                <div class=\"row\">\n                    <div class=\"col-lg-12\">\n                        <div class=\"bs-component\">\n                            <table class=\"table table-striped table-hover \">\n                                <thead>\n                                <tr>\n                                    <th>Hierarchy</th>\n                                    <th class=\"text-right\">Inactivated Concepts</th>\n                                    <th class=\"text-right\">% of Hierarchy Inactivated</th>\n                                    <th class=\"text-right\">% of All Inactivated</th>\n                                    <th class=\"text-right\">% as Ambiguous</th>\n                                    <th class=\"text-right\">% as Duplicate</th>\n                                    <th class=\"text-right\">% as Erroneous</th>\n                                    <th class=\"text-right\">% as Not Stated</th>\n                                    <th class=\"text-right\">% as Outdated</th>\n                                    <th class=\"text-right\">% as Moved</th>\n                                    <th class=\"text-right\">% as Limited</th>\n                                </tr>\n                                </thead>\n                                <tbody>\n                                ";
  stack1 = helpers.each.call(depth0, (depth0 && depth0.rows), {hash:{},inverse:self.noop,fn:self.program(1, program1, data),data:data});
  if(stack1 || stack1 === 0) { buffer += stack1; }
  buffer += "\n                                </tbody>\n                            </table>\n                        </div><!-- /example -->\n                    </div>\n                </div>\n            </div>\n            <footer class=\"card-footer\">\n                <!--<button class=\"btn btn-flat\">Share</button>-->\n                <!--<button class=\"btn btn-flat btn-warning\">Learn More</button>-->\n            </footer>\n        </div>\n    </div>\n</div>\n";
  return buffer;
  });

this["JST"]["views/treemap.hbs"] = Handlebars.template(function (Handlebars,depth0,helpers,partials,data) {
  this.compilerInfo = [4,'>= 1.0.0'];
helpers = this.merge(helpers, Handlebars.helpers); data = data || {};
  


  return "<div class=\"col-md-12\">\n    <div class=\"card\">\n        <div class=\"card-height-indicator\"></div>\n        <div class=\"card-content\">\n            <div class=\"card-body\">\n                <div cass=\"container\">\n                    <h2>TreeMaps</h2>\n                    <p id=\"chart\"></p>\n\n                        <aside>Click any cell to zoom in, or the top orange label to zoom out.</aside>\n\n                    <div>\n                        <select>\n                            <option value=\"descendantCount\">Descendants count</option>\n                            <option value=\"reactivatedCount\">Reactivated concepts</option>\n                            <option value=\"newConceptsCount\">New concepts</option>\n                            <option value=\"changedFSNCount\">Changed FSN</option>\n                            <option value=\"inactDescrCount\">Inactivated descriptions</option>\n                            <option value=\"newDescrCount\">New descriptions</option>\n                            <option value=\"reactDescrCount\">Reactivated descriptions</option>\n                            <!--<option value=\"changedAccepCount\">Changed acceptabilitiy</option>-->\n                        </select>\n                    </div>\n                </div>\n            </div>\n            <footer class=\"card-footer\">\n                <!--<button class=\"btn btn-flat\">Share</button>-->\n                <!--<button class=\"btn btn-flat btn-warning\">Learn More</button>-->\n            </footer>\n        </div>\n    </div>\n</div>\n";
  });