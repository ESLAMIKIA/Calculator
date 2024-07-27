fun onDigitClick(view: View) {
        currentInput += (view as Button).text
        binding.textView.text = currentInput
    }
