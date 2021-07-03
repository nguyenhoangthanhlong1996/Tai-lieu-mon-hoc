
var xmlDoc = null;

document.getElementById('fileUpload').addEventListener("change", function () {
    document.getElementById('dom-tree').innerHTML = '';
    document.getElementById('list-dom').innerHTML = '';
    parseDomFromInputFile().then(doc => {
        xmlDoc = doc;
    }).catch(err => {xmlDoc = null});
});

document.getElementById('btnDrawDomTree').addEventListener("click", function () {
    if (xmlDoc) {
        document.getElementById('dom-tree').innerHTML = htmlDomTree(xmlDoc.childNodes);
    } else {
        alert('You must select the xml file');
    }
});

document.getElementById('btnSearch').addEventListener("click", function () {
    if (xmlDoc) {
        var option = document.getElementById('selectTypeQuery').value;
        var value = document.getElementById('txtQueryValue').value;
        value = value ? value.trim() : null;
        var nodes = null;
        if (value) {
            if (option === 'tag') {
                nodes = Array.from(xmlDoc.getElementsByTagName(value));
            } else if (option === 'id') {
                var node = xmlDoc.getElementById(value);
                nodes = node ? [node] : [];
            }
            var html = document.getElementById('list-dom').innerHTML;
            html = nodes.length === 0 ? '0 result found !' : '';
            nodes.forEach((node, idx) => {
                html += `result ${idx + 1}\n<ul class="dom">${htmlDomTree([node])}</ul>\n`;
            });
            document.getElementById('list-dom').innerHTML = html;
        }
    } else {
        alert('You must select the xml file');
    }
});

function parseDomFromInputFile() {
    return new Promise((resolve, reject) => {
        var file = document.getElementById("fileUpload").files[0];
        if (file) {
            if (file.type !== 'text/xml') {
                alert('Support only file type text/xml');
                document.getElementById("fileUpload").value = '';
                reject();
                return;
            }
            var reader = new FileReader();
            reader.readAsText(file, "UTF-8");
            reader.onload = function (evt) {
                parser = new DOMParser();
                xmlDoc = parser.parseFromString(evt.target.result, "text/xml");
                if (xmlDoc.getElementsByTagName('parsererror').length > 0) {
                    alert('Invalid XML');
                    reject();
                    return;
                }
                resolve(xmlDoc);
            }
            reader.onerror = function (evt) {
                alert('Error reading file');
                reject();
                return;
            }
        } else {
            reject();
            return;
        }
    });
}

function htmlDomTree(nodeList) {
    var html = '';
    nodeList.forEach(node => {
        var nodeName = node.nodeName;
        var nodeType = node.nodeType;
        var attrs = node.attributes;
        var childNodes = node.childNodes;
        // Generate html of node
        if (nodeType === 1) { // Element Node
            html += `<li class="t1"><code>${nodeName}</code>`;
            for (i = 0; i < attrs.length; i++) {
                html += ` 
                    <span class="t2">
                        <code class="attribute name">${attrs[i].name}</code>="<code class="attribute value">${attrs[i].value}</code>"
                    </span>
                `;
            }
            // Generate html all child nodes
            var htmlChildNodes = htmlDomTree(childNodes);
            html += htmlChildNodes != '' ? `<ul>${htmlChildNodes}</ul></li>` : '</li>';
        } else if (nodeType === 3) { // Text Node
            html += `<li class="t3"><code>#text</code>: <span>${node.nodeValue}</span></li>`
        } else if (nodeType === 8) { // Comment Node
            html += `<li class="t8"><code>#comment</code>: <span>${node.nodeValue}</span></li>`
        }
    })
    return html;
}