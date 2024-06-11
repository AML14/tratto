document.addEventListener("DOMContentLoaded", function () {
    const fileInput = document.getElementById("file-input");
    const listContainer = document.getElementById("list-container");

    fileInput.addEventListener("change", function () {
        const file = this.files[0];
        if (file) {
            const reader = new FileReader();

            reader.onload = function (event) {
                const data = JSON.parse(event.target.result);
                displayList(data);
            };

            reader.readAsText(file);
        }
    });

    function displayList(list) {
        // Clear previous content
        listContainer.innerHTML = "";

        // Iterate through each object in the list
        list.forEach(item => {
            // Create elements to display the item details
            const itemContainer = document.createElement("div");
            itemContainer.classList.add("item-external-container");

            const customIdContainer = document.createElement("div");
            customIdContainer.classList.add("item-container");
            const customIdLabel = document.createElement("label");
            customIdLabel.classList.add("item-label")
            const customIdSpan = document.createElement("span");
            customIdSpan.classList.add("item-span");
            customIdContainer.appendChild(customIdLabel);
            customIdContainer.appendChild(customIdSpan);
            customIdLabel.textContent = "Custom ID";
            customIdSpan.textContent = item.custom_id;


            const projectNameContainer = document.createElement("div");
            projectNameContainer.classList.add("item-container");
            const projectNameLabel = document.createElement("label");
            projectNameLabel.classList.add("item-label")
            const projectNameSpan = document.createElement("span");
            projectNameSpan.classList.add("item-span");
            projectNameContainer.appendChild(projectNameLabel);
            projectNameContainer.appendChild(projectNameSpan);
            projectNameLabel.textContent = "Project Name";
            projectNameSpan.textContent = item.projectName;

            const classNameContainer = document.createElement("div");
            classNameContainer.classList.add("item-container");
            const classNameLabel = document.createElement("label");
            classNameLabel.classList.add("item-label")
            const classNameSpan = document.createElement("span");
            classNameSpan.classList.add("item-span");
            classNameContainer.appendChild(classNameLabel);
            classNameContainer.appendChild(classNameSpan);
            classNameLabel.textContent = "Class Name";
            classNameSpan.textContent = item.className;

            const methodSignatureContainer = document.createElement("div");
            methodSignatureContainer.classList.add("item-container");
            const methodSignatureLabel = document.createElement("label");
            methodSignatureLabel.classList.add("item-label")
            const methodSignatureSpan = document.createElement("span");
            methodSignatureSpan.classList.add("item-span");
            methodSignatureContainer.appendChild(methodSignatureLabel);
            methodSignatureContainer.appendChild(methodSignatureSpan);
            methodSignatureLabel.textContent = "Method Signature";
            methodSignatureSpan.textContent = item.methodSignature;

            const methodSourceCodeContainer = document.createElement("div");
            methodSourceCodeContainer.classList.add("item-container");
            const methodSourceCodeLabel = document.createElement("label");
            methodSourceCodeLabel.classList.add("item-label")
            const methodSourceCodePre = document.createElement("pre");
            methodSourceCodePre.classList.add("item-pre");
            const methodSourceCodeCode = document.createElement("code");
            methodSourceCodeCode.classList.add("item-code");
            methodSourceCodeContainer.appendChild(methodSourceCodeLabel);
            methodSourceCodeContainer.appendChild(methodSourceCodePre);
            methodSourceCodePre.appendChild(methodSourceCodeCode);
            methodSourceCodeLabel.textContent = "Method Source Code";
            methodSourceCodeCode.textContent = item.methodSourceCode;

            const estimatedOraclesContainer = document.createElement("div");
            estimatedOraclesContainer.classList.add("item-container");
            const estimatedOraclesLabel = document.createElement("label");
            estimatedOraclesLabel.classList.add("item-label")
            const estimatedOraclesPre = document.createElement("pre");
            estimatedOraclesPre.classList.add("item-pre");
            const estimatedOraclesCode = document.createElement("code");
            estimatedOraclesCode.classList.add("item-code");
            estimatedOraclesContainer.appendChild(estimatedOraclesLabel);
            estimatedOraclesContainer.appendChild(estimatedOraclesPre);
            estimatedOraclesPre.appendChild(estimatedOraclesCode);
            estimatedOraclesLabel.textContent = "Estimated Oracles";
            estimatedOraclesCode.textContent = item.estimated_oracles;

            const responseContainer = document.createElement("div");
            responseContainer.classList.add("item-container");
            const responseLabel = document.createElement("label");
            responseLabel.classList.add("item-label")
            const responsePre = document.createElement("pre");
            responsePre.classList.add("item-pre");
            const responseCode = document.createElement("code");
            responseCode.classList.add("item-code");
            responseContainer.appendChild(responseLabel);
            responseContainer.appendChild(responsePre);
            responsePre.appendChild(responseCode);
            responseLabel.textContent = "Response";
            responseCode.textContent = item.response;

            // Append elements to item container
            itemContainer.appendChild(customIdContainer);
            itemContainer.appendChild(projectNameContainer);
            itemContainer.appendChild(classNameContainer);
            itemContainer.appendChild(methodSignatureContainer);
            itemContainer.appendChild(methodSourceCodeContainer);
            itemContainer.appendChild(estimatedOraclesContainer);
            itemContainer.appendChild(responseContainer);

            // Append item container to list container
            listContainer.appendChild(itemContainer);
        });
    }
});