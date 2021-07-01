var xhttp = new XMLHttpRequest();
var xmlDoc = null;
xhttp.onreadystatechange = function () {
    if (this.readyState == 4 && this.status == 200) {
        myFunction(this);
    }
};
xhttp.open("GET", "books.xml", true);
xhttp.send();

function myFunction(xml) {
    xmlDoc = xml.responseXML;
}

document.getElementById('btnDrawDomTree').addEventListener("click", function () {
    parseDomFromInputFile().then(xmlDoc => {
        console.log(xmlDoc);
        if (xmlDoc) {
            document.getElementById('domTree').innerHTML = htmlDomTree(xmlDoc.childNodes);
        }
    });
});

document.getElementById('btnSearch').addEventListener("click", function () {
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
        console.log(nodes);
        var html = document.getElementById('wrapDom').innerHTML;
        html = '';
        nodes.forEach((node, idx) => {
            html += `result ${idx + 1}\n<ul class="dom">${htmlDomTree([node])}</ul>\n`;
        });
        document.getElementById('wrapDom').innerHTML = html;
    }
});

function parseDomFromInputFile() {
    return new Promise((resolve, reject) => {
        var file = document.getElementById("fileUpload").files[0];
        if (file) {
            if (file.type !== 'text/xml') {
                alert('Support only file type text/xml');
                reject();
            }
            var reader = new FileReader();
            reader.readAsText(file, "UTF-8");
            reader.onload = function (evt) {
                parser = new DOMParser();
                xmlDoc = parser.parseFromString(evt.target.result, "text/xml");
                if (xmlDoc.getElementsByTagName('parsererror').length > 0) {
                    alert('Invalid XML');
                    reject();
                }
                resolve(xmlDoc);
            }
            reader.onerror = function (evt) {
                alert('Error reading file');
                reject();
            }
        } else {
            alert('You must select the xml file')
            reject();
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